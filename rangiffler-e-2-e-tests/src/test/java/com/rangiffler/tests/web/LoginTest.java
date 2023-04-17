package com.rangiffler.tests.web;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.LoginPage;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.StartPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector.METHOD;
import static com.rangiffler.tests.web.error.ErrorMessage.BAD_CREDENTIALS;


@Epic("[WEB][rangiffler-frontend]: Authorization")
@DisplayName("[WEB][rangiffler-frontend]: Authorization")
public class LoginTest extends BaseWebTest {

    @Test
    @AllureId("500006")
    @DisplayName("WEB: The main page should be displayed after logging in as a registered user")
    @Tag("WEB")
    @GenerateUser
    void mainPageShouldBeDisplayedAfterSuccessLogin(@User(selector = METHOD) UserJson user) {
        Selenide.open(StartPage.URL, StartPage.class)
                .doLogin()
                .fillLoginPage(user.getUsername(), user.getPassword())
                .submit(new MainPage())
                .waitForPageLoaded();
    }


    @Test
    @AllureId("500005")
    @DisplayName("WEB: If the username/password is entered incorrectly, the user remains unauthorized")
    @Tag("WEB")
    @GenerateUser()
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials(@User(selector = METHOD) UserJson user) {
        LoginPage loginPage = Selenide.open(StartPage.URL, StartPage.class)
                .doLogin()
                .fillLoginPage(user.getUsername(), "incorrect" + user.getPassword());

        loginPage.submit(loginPage)
                .checkError(BAD_CREDENTIALS);
    }
}
