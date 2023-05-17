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
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector.METHOD;
import static com.rangiffler.tests.message.Message.BAD_CREDENTIALS;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;


@Epic("[WEB][rangiffler-frontend]: Authorization")
@DisplayName("[WEB][rangiffler-frontend]: Authorization")
public class LoginTest extends BaseWebTest {

    @Test
    @AllureId("500006")
    @DisplayName("WEB: The main page should be displayed after logging in as a registered user")
    @Tag("WEB")
    @Severity(BLOCKER)
    @GenerateUser
    void mainPageShouldBeDisplayedAfterSuccessLogin(@User(selector = METHOD) UserJson user) {
        step("Open the browser", ()-> open("", StartPage.class))
                .doLogin()
                .fillLoginPage(user.getUsername(), user.getPassword())
                .submit(new MainPage())
                .verifyApplicationName("Rangiffler");
    }

    @Test
    @AllureId("500005")
    @DisplayName("WEB: If the username/password is entered incorrectly, the user remains unauthorized")
    @Tag("WEB")
    @Severity(CRITICAL)
    @GenerateUser
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials(@User(selector = METHOD) UserJson user) {
        StartPage startPage = step("Open the browser",() -> Selenide.open("", StartPage.class));
        LoginPage loginPage = startPage
                .doLogin()
                .fillLoginPage(user.getUsername(), "incorrect" + user.getPassword());

        loginPage.submit(loginPage)
                .checkError(BAD_CREDENTIALS);
    }
}
