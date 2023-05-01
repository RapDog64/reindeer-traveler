package com.rangiffler.jupiter.extension;

import com.rangiffler.api.service.AuthenticationClient;
import com.rangiffler.api.service.UserdataClient;
import com.rangiffler.jupiter.annotation.InjectUser;
import com.rangiffler.model.UserJson;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import retrofit2.Response;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static com.rangiffler.utility.DataGenerator.generateRandomPassword;
import static com.rangiffler.utility.DataGenerator.generateRandomUsername;

public class CreateTestUserExtension implements TestInstancePostProcessor {
    private final AuthenticationClient authClient = new AuthenticationClient();
    private final UserdataClient userdataClient = new UserdataClient();


    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        List<Field> fields = Arrays.stream(testInstance.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(InjectUser.class))
                .filter(field -> field.getType() == UserJson.class)
                .peek(field -> field.setAccessible(true))
                .toList();

        for (Field field : fields) {
            UserJson createdUser = apiRegister(generateRandomUsername(), generateRandomPassword());
            field.set(testInstance, createdUser);
        }
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
}
