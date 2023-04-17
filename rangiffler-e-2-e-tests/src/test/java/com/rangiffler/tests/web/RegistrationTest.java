package com.rangiffler.tests.web;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Registration")
@Epic("[WEB][rangiffler-frontend]: Registration")
@DisplayName("[WEB][rangiffler-frontend]: Registration")
public class RegistrationTest extends BaseWebTest {

    @Test
    @Disabled
    @AllureId("500001")
    @Tag("WEB")
    @DisplayName("WEB: A user should register a new account with valid credentials successfully")
    void shouldRegisterNewUserAccount() {
    }

    @Test
    @Disabled
    @AllureId("500002")
    @Tag("WEB")
    @DisplayName("Register form should return an error message if password and confirm password are different")
    void shouldReturnErrorMessageIfPasswordAndConfirmPasswordDifferent() {
        String expectedMessage = "Passwords should be equal";
    }

    @Test
    @AllureId("500003")
    @DisplayName("WEB: Register form should return an error message if provided username is already exist")
    @Tag("WEB")
    void shouldNotRegisterUserIfUsernameAlreadyExist() {
    }

    @Test
    @AllureId("500004")
    @DisplayName("WEB: Register form should return an error message if password length should less than 3 characters")
    void shouldReturnErrorMessageIfPasswordLengthNotInThePasswordRange() {
        // TODO: Should be a parametrized test.
        // TODO: The first case is less 3 characters.
        // TODO: The second case is more 12 characters.
    }
}
