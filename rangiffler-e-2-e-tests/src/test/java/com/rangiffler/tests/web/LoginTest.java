package com.rangiffler.tests.web;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.StartPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.jupiter.extension.CreateUserExtension.Selector.METHOD;


@Epic("[WEB][rangiffler-frontend]: Авторизация")
@DisplayName("[WEB][rangiffler-frontend]: Авторизация")
public class LoginTest extends BaseWebTest {

    @Test
    @Disabled
    @AllureId("500001")
    @DisplayName("WEB: Главная страница должна отображаться после логина новым юзером")
    @Tag("WEB")
    @GenerateUser
    void mainPageShouldBeDisplayedAfterSuccessLogin(@User(selector = METHOD) UserJson user) {
        Selenide.open("", StartPage.class)
                .doLogin()
                .fillLoginPage("", "")
                .submit(new MainPage())
                .waitForPageLoaded();
    }
}
