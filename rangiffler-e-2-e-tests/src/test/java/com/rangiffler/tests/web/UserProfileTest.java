package com.rangiffler.tests.web;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.ProfilePage;
import com.rangiffler.utility.DataGenerator;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.rangiffler.page.component.HeaderItem.USER_PROFILE;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Epic("[WEB][rangiffler-frontend]: User Profile")
@DisplayName("[WEB][rangiffler-frontend]: User Profile")
public class UserProfileTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to update his profile")
    @Tag("WEB")
    @AllureId("500012")
    @Severity(BLOCKER)
    @ApiLogin(user = @GenerateUser)
    void shouldUpdateUserProfile(@User UserJson user) {
        final String firstname = DataGenerator.generateRandomFirstname();
        final String lastname = DataGenerator.generateRandomLastname();

        Selenide.open("", MainPage.class)
                .getHeader()
                .clickOn(USER_PROFILE, new ProfilePage())
                .typeFirstname(firstname)
                .typeLastname(lastname)
                .save()
                .waitForPageLoaded()
                .getHeader()
                .clickOn(USER_PROFILE, new ProfilePage())
                .waitForPageLoaded()
                .validateUserProfile(firstname, lastname);
    }

    @Test
    @Disabled("[RAN-2345] Broken the upload photo functionality")
    @DisplayName("WEB: User should be able to update his profile avatar")
    @Tag("WEB")
    @AllureId("500021")
    @Severity(BLOCKER)
    @ApiLogin(user = @GenerateUser)
    void shouldUpdateUserAvatar(@User UserJson user) {
        step("Open the browser", () -> open("", MainPage.class))
                .getHeader()
                .clickOn(USER_PROFILE, new ProfilePage())
                .uploadAvatar("photos/ava.jpeg")
                .save()
                .waitForPageLoaded()
                .getHeader()
                .clickOn(USER_PROFILE, new ProfilePage())
                .waitForPageLoaded();
    }
}
