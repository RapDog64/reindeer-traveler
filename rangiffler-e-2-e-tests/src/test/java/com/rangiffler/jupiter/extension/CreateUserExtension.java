package com.rangiffler.jupiter.extension;

import com.rangiffler.api.service.AuthenticationClient;
import com.rangiffler.api.service.CountryClient;
import com.rangiffler.api.service.PhotoClient;
import com.rangiffler.api.service.UserdataClient;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.Friends;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.IncomeInvitations;
import com.rangiffler.jupiter.annotation.OutcomeInvitations;
import com.rangiffler.jupiter.annotation.TravelPhotos;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.FriendJson;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.model.enums.Country;
import io.qameta.allure.AllureId;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.rangiffler.jupiter.extension.BeforeSuiteExtension.ALL_COUNTRIES;
import static com.rangiffler.utility.DataGenerator.generatePhoto;
import static com.rangiffler.utility.DataGenerator.generateRandomPassword;
import static com.rangiffler.utility.DataGenerator.generateRandomSentence;
import static com.rangiffler.utility.DataGenerator.generateRandomUsername;

public class CreateUserExtension implements BeforeEachCallback, ParameterResolver {

    private final AuthenticationClient authClient = new AuthenticationClient();
    private final UserdataClient userdataClient = new UserdataClient();
    private final CountryClient countryClient = new CountryClient();
    private final PhotoClient photoClient = new PhotoClient();

    public static final ExtensionContext.Namespace
            ON_METHOD_USERS_NAMESPACE = ExtensionContext.Namespace.create(CreateUserExtension.class, Selector.METHOD),
            API_LOGIN_USERS_NAMESPACE = ExtensionContext.Namespace.create(CreateUserExtension.class, Selector.NESTED);


    @Step("Create a new user for test")
    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final String testId = getTestId(context);
        Map<Selector, GenerateUser> userAnnotations = extractGenerateUserAnnotations(context);
        for (Map.Entry<Selector, GenerateUser> entry : userAnnotations.entrySet()) {
            final GenerateUser generateUser = entry.getValue();
            String username = generateUser.username();
            String password = generateUser.password();
            if ("".equals(username)) {
                username = generateRandomUsername();
            }
            if ("".equals(password)) {
                password = generateRandomPassword();
            }
            UserJson userJson = apiRegister(username, password);

            createFriendsIfPresent(generateUser, userJson);
            createTravelPhotosIfPresent(generateUser, userJson);
            createIncomeInvitationsIfPresent(generateUser, userJson);
            createOutcomeInvitationsIfPresent(generateUser, userJson);

            context.getStore(entry.getKey().getNamespace()).put(testId, userJson);
        }
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext,
                                     final ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserJson.class)
                && parameterContext.getParameter().isAnnotationPresent(User.class);
    }

    @Override
    public UserJson resolveParameter(final ParameterContext parameterContext,
                                     final ExtensionContext extensionContext) throws ParameterResolutionException {
        final String testId = getTestId(extensionContext);
        User annotation = parameterContext.getParameter().getAnnotation(User.class);
        return extensionContext.getStore(annotation.selector().getNamespace()).get(testId, UserJson.class);
    }

    private void createTravelPhotosIfPresent(GenerateUser generateUser, UserJson userJson) throws IOException {
        TravelPhotos travels = generateUser.travels();
        if (travels.handleAnnotation() && travels.count() > 0) {
            for (int i = 0; i < travels.count(); i++) {
                String description = travels.description();
                if ("".equals(description)) {
                    description = generateRandomSentence(5);
                }
                String pathToImage = travels.imgPath();
                CountryJson country = getCountry(travels.country());
                PhotoJson photoJson = generatePhoto(country, userJson.getUsername(), description, pathToImage);
                photoClient.addPhoto(photoJson);
            }
        }
    }

    private CountryJson getCountry(Country country) throws IOException {
        CountryJson selectedCountry = ALL_COUNTRIES.stream()
                .filter(countryJson -> countryJson.getCode().equals(country.code))
                .findFirst()
                .orElseThrow();
        return countryClient.findCountryById(selectedCountry.getId());
    }

    private void createFriendsIfPresent(GenerateUser generateUser, UserJson createdUser) throws Exception {
        Friends friends = generateUser.friends();
        if (friends.handleAnnotation() && friends.count() > 0) {
            for (int i = 0; i < friends.count(); i++) {
                UserJson friend = apiRegister(generateRandomUsername(), generateRandomPassword());
                FriendJson addFriend = new FriendJson();
                FriendJson invitation = new FriendJson();
                addFriend.setUsername(friend.getUsername());
                invitation.setUsername(createdUser.getUsername());
                userdataClient.addFriend(createdUser.getUsername(), addFriend);
                userdataClient.acceptInvitation(friend.getUsername(), invitation);
                createdUser.getFriendsList().add(friend);
            }
        }
    }

    private void createIncomeInvitationsIfPresent(GenerateUser generateUser, UserJson createdUser) throws Exception {
        IncomeInvitations invitations = generateUser.incomeInvitations();
        if (invitations.handleAnnotation() && invitations.count() > 0) {
            for (int i = 0; i < invitations.count(); i++) {
                UserJson invitation = apiRegister(generateRandomUsername(), generateRandomPassword());
                FriendJson addFriend = new FriendJson();
                addFriend.setUsername(createdUser.getUsername());
                userdataClient.addFriend(invitation.getUsername(), addFriend);
                createdUser.getInvitationsJsons().add(invitation);
            }
        }
    }

    private void createOutcomeInvitationsIfPresent(GenerateUser generateUser, UserJson createdUser) throws Exception {
        OutcomeInvitations invitations = generateUser.outcomeInvitations();
        if (invitations.handleAnnotation() && invitations.count() > 0) {
            for (int i = 0; i < invitations.count(); i++) {
                UserJson friend = apiRegister(generateRandomUsername(), generateRandomPassword());
                FriendJson addFriend = new FriendJson();
                addFriend.setUsername(friend.getUsername());
                userdataClient.addFriend(createdUser.getUsername(), addFriend);
                createdUser.getInvitationsJsons().add(friend);
            }
        }
    }


    private String getTestId(final ExtensionContext context) {
        return Objects.requireNonNull(
                context.getRequiredTestMethod().getAnnotation(AllureId.class)
        ).value();
    }

    private Map<Selector, GenerateUser> extractGenerateUserAnnotations(final ExtensionContext context) {
        Map<Selector, GenerateUser> annotationsOnTest = new HashMap<>();
        GenerateUser annotationOnMethod = context.getRequiredTestMethod().getAnnotation(GenerateUser.class);
        if (annotationOnMethod != null && annotationOnMethod.handleAnnotation()) {
            annotationsOnTest.put(Selector.METHOD, annotationOnMethod);
        }
        ApiLogin apiLoginAnnotation = context.getRequiredTestMethod().getAnnotation(ApiLogin.class);
        if (apiLoginAnnotation != null && apiLoginAnnotation.user().handleAnnotation()) {
            annotationsOnTest.put(Selector.NESTED, apiLoginAnnotation.user());
        }
        return annotationsOnTest;
    }

    private UserJson apiRegister(String username, String password) throws Exception {
        authClient.authorize();
        Response<Void> res = authClient.register(username, password);
        if (res.code() != 201) {
            throw new RuntimeException("User is not registered");
        }
        UserJson currentUser = userdataClient.getCurrentUser(username);
        currentUser.setPassword(password);
        return currentUser;
    }

    public enum Selector {
        METHOD, NESTED;

        public ExtensionContext.Namespace getNamespace() {
            switch (this) {
                case METHOD -> {
                    return ON_METHOD_USERS_NAMESPACE;
                }
                case NESTED -> {
                    return API_LOGIN_USERS_NAMESPACE;
                }
                default -> throw new IllegalStateException();
            }
        }
    }
}
