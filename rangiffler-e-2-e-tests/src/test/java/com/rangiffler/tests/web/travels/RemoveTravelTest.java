package com.rangiffler.tests.web.travels;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.Travels;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.YourTravelComponent;
import com.rangiffler.tests.web.BaseWebTest;
import com.rangiffler.tests.web.message.Message;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.model.enums.Country.*;
import static com.rangiffler.page.component.TabItem.YOUR_TRAVELS;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
public class RemoveTravelTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to remove his travel")
    @Tag("WEB")
    @AllureId("500015")
    @Severity(BLOCKER)
    @ApiLogin(user = @GenerateUser(travels = @Travels(country = GERMANY, count = 1)))
    public void shouldRemoveTravel(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .waitForPageLoaded()
                .openTab(YOUR_TRAVELS, new YourTravelComponent())
                .openTravelCard(GERMANY)
                .removePhoto()
                .confirmRemoving()
                .verifyTravelRemoved(Message.PHOTO_DELETED);
    }

    @Test
    @DisplayName("WEB: If user doesn't confirm, user's travel should not be removed")
    @Tag("WEB")
    @AllureId("500016")
    @Severity(CRITICAL)
    @ApiLogin(user = @GenerateUser(travels = @Travels(country = GERMANY, count = 1)))
    void shouldNotRemoveTravel(@User UserJson user) {
        Selenide.open("", MainPage.class)
                .waitForPageLoaded()
                .openTab(YOUR_TRAVELS, new YourTravelComponent())
                .openTravelCard(GERMANY)
                .removePhoto()
                .declineConfirmation();
    }
}
