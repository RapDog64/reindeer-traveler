package com.rangiffler.tests.web.travels;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.Travels;
import com.rangiffler.model.enums.Country;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.PeopleAroundComponent;
import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.rangiffler.model.enums.Country.*;
import static com.rangiffler.page.component.TabItem.PEOPLE_AROUND;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("[WEB][rangiffler-frontend]: Travels")
@DisplayName("[WEB][rangiffler-frontend]: Travels")
//@Disabled("Need to finish the test")
public class RemoveTravelTest extends BaseWebTest {

    @Test
    @DisplayName("WEB: User is able to remove his travel")
    @Tag("WEB")
    @AllureId("500015")
    @Severity(BLOCKER)
    @ApiLogin(user = @GenerateUser(travels = @Travels(country = GERMANY, count = 1)))
    public void shouldRemoveTravel() {
        Selenide.open("", MainPage.class)
                .waitForPageLoaded()
                .openTab(PEOPLE_AROUND, new PeopleAroundComponent());
    }

    @Test
    @DisplayName("WEB: If user doesn't confirm, user's travel should not be removed")
    @Tag("WEB")
    @AllureId("500016")
    @Severity(CRITICAL)
    void shouldNotRemoveTravel() {
    }
}
