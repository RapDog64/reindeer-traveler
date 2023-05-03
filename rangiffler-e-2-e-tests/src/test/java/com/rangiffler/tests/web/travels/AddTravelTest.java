package com.rangiffler.tests.web.travels;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.model.enums.Country;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.AddTravelForm;
import com.rangiffler.page.component.HeaderItem;
import com.rangiffler.page.component.YourTravelComponent;
import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.model.enums.Country.*;
import static com.rangiffler.utility.DataGenerator.generateRandomSentence;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
public class AddTravelTest extends BaseWebTest {

    @Test
    @Disabled("[RAN-2345] Broken the upload photo functionality")
    @DisplayName("WEB: User is able to add a new travel")
    @Tag("WEB")
    @Severity(BLOCKER)
    @AllureId("500013")
    void shouldAddNewTravel() {
    }

    @Test
    @DisplayName("WEB: The 'Save' button is disabled if a photo had not been upload")
    @Tag("WEB")
    @Severity(NORMAL)
    @AllureId("500014")
    @ApiLogin(user = @GenerateUser)
    void shouldNotSaveTravelWithoutPhoto(@User UserJson user) {
        final String description = generateRandomSentence(2);

        Selenide.open("", MainPage.class)
                .waitForPageLoaded()
                .getHeader()
                .clickOn(HeaderItem.ADD_PHOTO, new AddTravelForm())
                .waitForPageLoaded()
                .fillTravelCard(RUSSIA, description)
                .verifySaveButtonIsDisabled();
    }
}
