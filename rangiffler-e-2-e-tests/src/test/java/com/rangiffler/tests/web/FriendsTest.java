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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB][rangiffler-frontend]: Friends")
@DisplayName("[WEB][rangiffler-frontend]: Friends")
public class FriendsTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User should see the list of his friends")
    @Tag("WEB")
    @AllureId("500007")
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 3)))
    void userShouldSeeTListOfFriends(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .getHeader()
                .clickOn(PanelAttribute.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendList(user.getFriendsList().size());
    }

    @Test
    @DisplayName("WEB: User should see an empty list of friends if the user does not any.")
    @Tag("WEB")
    @AllureId("500008")
    @ApiLogin(user = @GenerateUser)
    void userShouldSeeEmptyListOfFriends(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .getHeader()
                .clickOn(PanelAttribute.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendListIsEmpty();
    }

    @Test
    @DisplayName("WEB: Amount of the user's friends at the header panel")
    @Tag("WEB")
    @AllureId("500009")
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 1)))
    void shouldDisplayAmountOfUsersFriendsAtHeaderPanel(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .getHeader()
                .verifyAmountFriends(user.getFriendsList().size());
    }

    @Test
    @Disabled
    @DisplayName("WEB: Amount of the user's friends at the header panel should be the same as in the user friend list")
    @Tag("WEB")
    @AllureId("500010")
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 1)))
    void shouldDisplaySameAmountOfFriendIn(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .getHeader()
                .clickOn(PanelAttribute.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendAdded(user.getFriendsList().get(0));
    }
}
