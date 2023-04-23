package com.rangiffler.tests.web.travels;

import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
public class AddTravelTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to add a new travel")
    @Tag("WEB")
    @AllureId("500013")
    void shouldAddNewTravel() {
    }

    @Test
    @DisplayName("WEB: The 'Save' button is disabled if photo is not upload")
    @Tag("WEB")
    @AllureId("500014")
    void shouldNotSaveTravelWithoutPhoto() {
    }
}
