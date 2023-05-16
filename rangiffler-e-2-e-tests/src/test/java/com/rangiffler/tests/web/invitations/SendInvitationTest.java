package com.rangiffler.tests.web.invitations;

import com.rangiffler.jupiter.annotation.ApiLogin;
import com.rangiffler.jupiter.annotation.GenerateUser;
import com.rangiffler.jupiter.annotation.InjectUser;
import com.rangiffler.jupiter.annotation.User;
import com.rangiffler.jupiter.extension.CreateTestUserExtension;
import com.rangiffler.model.UserJson;
import com.rangiffler.page.MainPage;
import com.rangiffler.page.component.PeopleAroundComponent;
import com.rangiffler.tests.web.BaseWebTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static com.rangiffler.page.component.TabItem.PEOPLE_AROUND;
import static io.qameta.allure.Allure.step;

@Epic("[WEB][rangiffler-frontend]: Invitations")
@DisplayName("[WEB][rangiffler-frontend]: Invitations")
@ExtendWith({CreateTestUserExtension.class})
public class SendInvitationTest extends BaseWebTest {

    @InjectUser
    private UserJson testUser;

    @Test
    @AllureId("500018")
    @ApiLogin(user = @GenerateUser)
    @DisplayName("User should be able to send a friend invitation to another user")
    void shouldSendFriendInvitation(@User UserJson user) {
        step("Open the browser", () -> open("", MainPage.class))
                .waitForPageLoaded()
                .openTab(PEOPLE_AROUND, new PeopleAroundComponent())
                .waitForPageLoaded()
                .sendInvitation(testUser.getUsername())
                .verifyInvitationSent(testUser.getUsername());
    }
}
