package com.rangiffler.tests.web;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.Friends;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.FriendPage;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.PanelAttribute;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.TRIVIAL;

@Epic("[WEB][rangiffler-frontend]: Friends")
@DisplayName("[WEB][rangiffler-frontend]: Friends")
public class FriendsTest extends BaseWebTest {

    @Test
    @Tag("WEB")
    @AllureId("500007")
    @Severity(CRITICAL)
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 3)))
    @DisplayName("WEB: User should see the list of his friends")
    void userShouldSeeTListOfFriends(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .getHeader()
                .waitForPageLoaded()
                .clickOn(PanelAttribute.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendList(user.getFriendsList().size());
    }

    @Test
    @Tag("WEB")
    @Severity(CRITICAL)
    @AllureId("500008")
    @ApiLogin(user = @GenerateUser)
    @DisplayName("WEB: User should see an empty list of friends if the user does not any.")
    void userShouldSeeEmptyListOfFriends(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .getHeader()
                .waitForPageLoaded()
                .clickOn(PanelAttribute.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendListIsEmpty();
    }

    @Test
    @DisplayName("WEB: Amount of the user's friends at the header panel")
    @Tag("WEB")
    @AllureId("500009")
    @Severity(TRIVIAL)
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 1)))
    void shouldDisplayAmountOfUsersFriendsAtHeaderPanel(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .waitForPageLoaded()
                .getHeader()
                .waitForPageLoaded()
                .verifyAmountFriends(user.getFriendsList().size());
    }
}
