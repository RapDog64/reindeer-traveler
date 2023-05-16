package com.rangiffler.tests.web;

import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.StartPage;
import com.rangiffler.utility.DataGenerator;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.open;
import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector;
import static com.rangiffler.tests.message.Message.ALLOWED_PASSWORD_ERROR_MSG;
import static com.rangiffler.tests.message.Message.PASSWORDS_SHOULD_BE_EQUAL;
import static com.rangiffler.tests.message.Message.USER_ALREADY_REGISTERED;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.MINOR;

@Epic("[WEB][rangiffler-frontend]: Registration")
@DisplayName("[WEB][rangiffler-frontend]: Registration")
public class RegistrationTest extends BaseWebTest {

    @Test
    @AllureId("500001")
    @Tag("WEB")
    @Severity(BLOCKER)
    @DisplayName("WEB: A user should register a new account with valid credentials successfully")
    void shouldRegisterNewUserAccount() {
        final String username = DataGenerator.generateRandomUsername();
        final String password = DataGenerator.generateRandomPassword();

        step("Open the browser", () -> open("", StartPage.class))
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
    @Severity(MINOR)
    @DisplayName("Register form should return an error message if password and confirm password are different")
    void shouldReturnErrorMessageIfPasswordAndConfirmPasswordDifferent() {
        final String username = DataGenerator.generateRandomUsername();
        final String password = DataGenerator.generateRandomPassword();
        final String confirmationPassword = DataGenerator.generateRandomPassword();

        step("Open the browser", () -> open("", StartPage.class))
                .doRegister()
                .fillRegisterForm(username, password, confirmationPassword)
                .successSubmit()
                .checkErrorMessage(PASSWORDS_SHOULD_BE_EQUAL);
    }

    @Test
    @AllureId("500003")
    @Tag("WEB")
    @GenerateUser
    @Severity(CRITICAL)
    @DisplayName("WEB: Register form should return an error message if provided username is already exist")
    void shouldNotRegisterUserIfUsernameAlreadyExist(@User(selector = Selector.METHOD) UserJson user) {
        final String password = DataGenerator.generateRandomPassword();

        step("Open the browser", () -> open("", StartPage.class))
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
    @Severity(MINOR)
    @ParameterizedTest(name = "WEB: Register form should return an error message if password length is {1} characters ")
    void shouldReturnErrorMessageIfPasswordLengthNotInThePasswordRange(String password, String displayName) {
        step("Open the browser", () -> open("", StartPage.class))
                .doRegister()
                .setUsername(DataGenerator.generateRandomUsername())
                .setPassword(password)
                .setPasswordSubmit(password)
                .successSubmit()
                .checkErrorMessage(ALLOWED_PASSWORD_ERROR_MSG);
    }
}
