package com.rangiffler.tests.web;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.StartPage;
import com.rangiffler.utility.DataGenerator;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import static com.rangiffler.tests.web.error.ErrorMessage.ALLOWED_PASSWORD_ERROR_MSG;
import static com.rangiffler.tests.web.error.ErrorMessage.PASSWORDS_SHOULD_BE_EQUAL;
import static com.rangiffler.tests.web.error.ErrorMessage.USER_ALREADY_REGISTERED;

@Epic("Registration")
@Epic("[WEB][rangiffler-frontend]: Registration")
@DisplayName("[WEB][rangiffler-frontend]: Registration")
public class RegistrationTest extends BaseWebTest {

    @Test
    @AllureId("500001")
    @Tag("WEB")
    @DisplayName("WEB: A user should register a new account with valid credentials successfully")
    void shouldRegisterNewUserAccount() {
        final String username = DataGenerator.generateRandomUsername();
        final String password = DataGenerator.generateRandomPassword();

        Selenide.open(StartPage.URL, StartPage.class)
                .doRegister()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .successSubmit()
                .proceedLoginLink()
                .waitForPageLoaded()
                .fillLoginPage(username, password)
                .submit(new MainPage())
                .verifyApplicationName("Rangiffler");
    }

    @Test
    @AllureId("500002")
    @Tag("WEB")
    @DisplayName("Register form should return an error message if password and confirm password are different")
    void shouldReturnErrorMessageIfPasswordAndConfirmPasswordDifferent() {
        final String username = DataGenerator.generateRandomUsername();
        final String password = DataGenerator.generateRandomPassword();
        final String confirmationPassword = DataGenerator.generateRandomPassword();

        Selenide.open(StartPage.URL, StartPage.class)
                .doRegister()
                .fillRegisterForm(username, password, confirmationPassword)
                .successSubmit()
                .checkErrorMessage(PASSWORDS_SHOULD_BE_EQUAL);
    }

    @Test
    @AllureId("500003")
    @DisplayName("WEB: Register form should return an error message if provided username is already exist")
    @Tag("WEB")
    @GenerateUser
    void shouldNotRegisterUserIfUsernameAlreadyExist(@User(selector = Selector.METHOD) UserJson user) {
        final String password = DataGenerator.generateRandomPassword();

        Selenide.open(StartPage.URL, StartPage.class)
                .doRegister()
                .fillRegisterForm(user.getUsername(), password, password)
                .successSubmit()
                .checkErrorMessage(String.format(USER_ALREADY_REGISTERED, user.getUsername()));
    }

    @CsvSource({
            "dm, less than 3",
            "d3fdfsjfdsad3fdasd231231, bigger than 12"
    })
    @AllureId("500004")
    @ParameterizedTest(name = "WEB: Register form should return an error message if password length is {1} characters ")
    void shouldReturnErrorMessageIfPasswordLengthNotInThePasswordRange(String password, String displayName) {
        Selenide.open(StartPage.URL, StartPage.class)
                .doRegister()
                .setUsername(DataGenerator.generateRandomUsername())
                .setPassword(password)
                .setPasswordSubmit(password)
                .successSubmit()
                .checkErrorMessage(ALLOWED_PASSWORD_ERROR_MSG);
    }
}
