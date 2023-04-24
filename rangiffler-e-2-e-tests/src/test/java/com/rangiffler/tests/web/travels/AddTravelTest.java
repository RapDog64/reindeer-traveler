package com.rangiffler.tests.web.travels;

import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.MINOR;

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
@Disabled("Need to finish the test")
public class AddTravelTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to add a new travel")
    @Tag("WEB")
    @Severity(BLOCKER)
    @AllureId("500013")
    void shouldAddNewTravel() {
    }

    @Test
    @DisplayName("WEB: The 'Save' button is disabled if photo is not upload")
    @Tag("WEB")
    @Severity(MINOR)
    @AllureId("500014")
    void shouldNotSaveTravelWithoutPhoto() {
    }
}
