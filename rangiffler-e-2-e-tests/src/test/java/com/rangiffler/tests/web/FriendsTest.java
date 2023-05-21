package com.rangiffler.tests.web;

import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.Friends;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.FriendPage;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.HeaderItem;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.TRIVIAL;

@Epic("[WEB][rangiffler-frontend]: Friends")
@DisplayName("[WEB][rangiffler-frontend]: Friends")
public class FriendsTest extends BaseWebTest {

    @Test
    @Tag("WEB")
    @AllureId("500010")
    @Severity(CRITICAL)
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 1)))
    @DisplayName("WEB: User should see the friend's username in the list.")
    void userShouldSeeFriendsUsernamesI(@User UserJson user) {
        final List<UserJson> friends = user.getFriendsList();

        step("Open the browser", () -> open("", MainPage.class))
                .getHeader()
                .waitForPageLoaded()
                .clickOn(HeaderItem.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendAdded(friends);
    }

    @Test
    @Tag("WEB")
    @AllureId("500007")
    @Severity(CRITICAL)
    @ApiLogin(user = @GenerateUser(friends = @Friends(count = 1)))
    @DisplayName("WEB: User should see the list of his friends")
    void userShouldSeeListOfFriends(@User UserJson user) {
        step("Open the browser", () -> open("", MainPage.class))
                .getHeader()
                .waitForPageLoaded()
                .clickOn(HeaderItem.FRIENDS, new FriendPage())
                .waitForPageLoaded()
                .verifyFriendList(user.getFriendsList().size());
    }

    @Test
    @Tag("WEB")
    @Severity(CRITICAL)
    @AllureId("500008")
    @ApiLogin(user = @GenerateUser)
    @DisplayName("WEB: User should see an empty list of friends if the user doesn't have any")
    void userShouldSeeEmptyListOfFriends(@User UserJson user) {
        step("Open the browser", () -> open("", MainPage.class))
                .getHeader()
                .waitForPageLoaded()
                .clickOn(HeaderItem.FRIENDS, new FriendPage())
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
        step("Open the browser", () -> open("", MainPage.class))
                .waitForPageLoaded()
                .getHeader()
                .waitForPageLoaded()
                .verifyAmountFriends(user.getFriendsList().size());
    }
}
