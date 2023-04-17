package com.rangiffler.tests.web;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
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
    void userShouldSeeTListOfFriends() {
    }

    @Test
    @DisplayName("WEB: User should see an empty list of friends if the user does not any.")
    @Tag("WEB")
    @AllureId("500008")
    void userShouldSeeEmptyListOfFriends() {
    }

    @Test
    @DisplayName("WEB: Amount of the user's friends at the header panel")
    @Tag("WEB")
    @AllureId("500009")
    void shouldDisplayAmountOfUsersFriendsAtHeaderPanel() {
    }

    @Test
    @DisplayName("WEB: Amount of the user's friends at the header panel should be the same as in the user friend list")
    @Tag("WEB")
    @AllureId("500010")
    void shouldDisplaySameAmountOfFriendIn() {
    }
}
