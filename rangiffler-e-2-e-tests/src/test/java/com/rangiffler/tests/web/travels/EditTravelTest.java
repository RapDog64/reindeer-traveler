package com.rangiffler.tests.web.travels;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.Travels;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.model.enums.Country;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.YourTravelComponent;
import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.model.enums.Country.GERMANY;
import static com.rangiffler.model.enums.Country.RUSSIA;
import static com.rangiffler.page.component.TabItem.YOUR_TRAVELS;
import static com.rangiffler.utility.DataGenerator.generateRandomSentence;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
public class EditTravelTest extends BaseWebTest {

    @Test
    @Tag("WEB")
    @AllureId("500017")
    @Severity(BLOCKER)
    @DisplayName("WEB: User is able to edit his travel")
    @ApiLogin(user = @GenerateUser(travels = @Travels(country = RUSSIA, count = 1)))
    void shouldEditTravel(@User UserJson user) {
        final Country country = GERMANY;
        final String description = generateRandomSentence(2);

        Selenide.open("", MainPage.class)
                .waitForPageLoaded()
                .openTab(YOUR_TRAVELS, new YourTravelComponent())
                .openTravelCard(RUSSIA)
                .editTravelCard()
                .fillTravelCard(country, description)
                .saveTravelCard()
                .openTravelCard(country)
                .verifyTravelCard(country, description);
    }
}
