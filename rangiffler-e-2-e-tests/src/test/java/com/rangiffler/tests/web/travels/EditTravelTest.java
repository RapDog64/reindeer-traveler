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

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
@Disabled("Need to finish the test")
public class EditTravelTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: user is able to edit his travel")
    @Tag("WEB")
    @AllureId("500017")
    @Severity(BLOCKER)
    void shouldEditTravel() {
    }
}
