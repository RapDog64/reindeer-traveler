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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.page.component.PanelAttribute.USER_PROFILE;
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
}
