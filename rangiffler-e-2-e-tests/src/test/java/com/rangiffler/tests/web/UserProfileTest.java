package com.rangiffler.tests.web;

import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB][rangiffler-frontend]: User Profile")
@DisplayName("[WEB][rangiffler-frontend]: User Profile")
public class UserProfileTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to update his profile")
    @Tag("WEB")
    @AllureId("500012")
    void shouldUpdateUserProfile() {
    }
}
