package com.rangiffler.tests.web.invitations;

import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.IncomeInvitations;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.PeopleAroundComponent;
import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.rangiffler.page.component.TabItem.PEOPLE_AROUND;
import static io.qameta.allure.Allure.step;

@Epic("[WEB][rangiffler-frontend]: Invitations")
@DisplayName("[WEB][rangiffler-frontend]: Invitations")
public class ReceiveInvitationTest extends BaseWebTest {

    @Test
    @AllureId("500019")
    @ApiLogin(user = @GenerateUser(incomeInvitations = @IncomeInvitations(count = 1)))
    @DisplayName("[WEB] User should be able to decline friend invitation")
    void shouldDeclineFriendInvitation(@User UserJson user) {
        final String username = user.getInvitationsJsons()
                .stream()
                .findFirst()
                .orElseThrow()
                .getUsername();

        step("Open the browser", () -> open("", MainPage.class))
                .waitForPageLoaded()
                .openTab(PEOPLE_AROUND, new PeopleAroundComponent())
                .waitForPageLoaded()
                .declineInvitation(username)
                .confirmDecliningFriendInvitation()
                .verifyFriendInvitationDeclined(username);
    }

    @Test
    @AllureId("500020")
    @ApiLogin(user = @GenerateUser(incomeInvitations = @IncomeInvitations(count = 1)))
    @DisplayName("[WEB] User should be able to accept friend invitation")
    void userShouldAcceptFriendInvitation(@User UserJson user) {
        final String username = user.getInvitationsJsons()
                .stream()
                .findFirst()
                .orElseThrow()
                .getUsername();

        step("Open the browser", () -> open("", MainPage.class))
                .waitForPageLoaded()
                .openTab(PEOPLE_AROUND, new PeopleAroundComponent())
                .waitForPageLoaded()
                .acceptInvitation(username)
                .verifyFriendInvitationAccepted(username);
    }
}
