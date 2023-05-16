package com.rangiffler.tests.web;

import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Epic("[WEB][rangiffler-frontend]: Log out")
@DisplayName("[WEB][rangiffler-frontend]: Log out")
public class LogoutTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to log out")
    @Tag("WEB")
    @AllureId("500011")
    @Severity(BLOCKER)
    @ApiLogin(user = @GenerateUser)
    void shouldDisplaySameAmountOfFriendIn(@User UserJson user) {
        step("Open the browser", () -> open("", MainPage.class))
                .waitForPageLoaded()
                .verifyApplicationName("Rangiffler")
                .doLogout()
                .waitForPageLoaded()
                .checkFormTitle("Be like Rangiffler");
    }
}
