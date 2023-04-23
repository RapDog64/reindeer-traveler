package com.rangiffler.tests.web;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB][rangiffler-frontend]: Log out")
@DisplayName("[WEB][rangiffler-frontend]: Log out")
public class LogoutTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: Usr is able to log out")
    @Tag("WEB")
    @AllureId("500011")
    @ApiLogin(user = @GenerateUser)
    void shouldDisplaySameAmountOfFriendIn(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .verifyApplicationName("Rangiffler")
                .doLogout()
                .waitForPageLoaded()
                .checkFormTitle("Be like Rangiffler");
    }
}
