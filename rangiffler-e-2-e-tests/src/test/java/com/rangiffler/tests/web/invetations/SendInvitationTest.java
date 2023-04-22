package com.rangiffler.tests.web.invetations;

import com.codeborne.selenide.Selenide;
import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.PeopleAroundComponent;
import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.rangiffler.page.component.TabItem.PEOPLE_AROUND;

@Epic("[WEB][rangiffler-frontend]: Invitations")
@DisplayName("[WEB][rangiffler-frontend]: Invitations")
public class SendInvitationTest extends BaseWebTest {

    @Test
    @AllureId("500018")
    @ApiLogin(user = @GenerateUser)
    void shouldSendFriendInvitation(@User UserJson user) {
        final String username = "cira.harber";

        Selenide.open("", MainPage.class)
                .navigateToTabs()
                .waitForPageLoaded()
                .openTab(PEOPLE_AROUND, new PeopleAroundComponent())
                .waitForPageLoaded()
                .sendInvitation(username)
                .verifyInvitationSent(username);
    }
}
