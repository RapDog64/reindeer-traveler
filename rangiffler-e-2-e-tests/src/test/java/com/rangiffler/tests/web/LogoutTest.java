package com.rangiffler.tests.web;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB][rangiffler-frontend]: Log out")
@DisplayName("[WEB][rangiffler-frontend]: Log out")
public class LogoutTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: Amount of the user's friends at the header panel should be the same as in the user friend list")
    @Tag("WEB")
    @AllureId("500011")
    void shouldDisplaySameAmountOfFriendIn() {
    }
}
