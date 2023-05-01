package com.rangiffler.page.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.rangiffler.model.enums.InvitationState.DECLINE_INVITATION;
import static com.rangiffler.tests.web.message.Message.ACCEPT_FRIEND_INVITATION_MESSAGE;
import static com.rangiffler.tests.web.message.Message.DECLINE_FRIEND_INVITATION_MESSAGE;
import static com.rangiffler.tests.web.message.Message.INVITATION_SENT_MESSAGE;

public class PeopleAroundComponent extends BasePage<PeopleAroundComponent> {

    private final ElementsCollection usernames = $$(testId("person-username"));
    private final SelenideElement confirmDeclineBtn = $("button[type='submit']");
    private final SelenideElement alertMessage = $("div[role='alert']");

    @Override
    @Step("Check that page is loaded")
    public PeopleAroundComponent waitForPageLoaded() {
        $(testId(TabItem.PEOPLE_AROUND.name)).shouldBe(visible);
        return this;
    }

    @Step("Find user in the list '{0}'")
    private SelenideElement findUser(String username) {
        return usernames.find(text(username)).parent();
    }

    @Step("Send friendship invitation to '{0}'")
    public PeopleAroundComponent sendInvitation(String username) {
        findUser(username).find("td[test-id='person-add']").find("button").click();
        return this;
    }

    @Step("Verify the invitation message is displayed")
    public PeopleAroundComponent verifyInvitationSent(String username) {
        alertMessage.shouldHave(text(String.format(INVITATION_SENT_MESSAGE, username)));
        return this;
    }

    @Step("Accept the {0}'s friend invitation")
    public PeopleAroundComponent acceptInvitation(String username) {
        findUser(username).find("div[role='group'] > button").click();
        return this;
    }

    @Step("Verify the friend invitation has been accepted")
    public PeopleAroundComponent verifyFriendInvitationAccepted(String username) {
        alertMessage.shouldHave(text(String.format(ACCEPT_FRIEND_INVITATION_MESSAGE, username)));
        return this;
    }

    @Step("Verify the friend invitation has been declined")
    public PeopleAroundComponent verifyFriendInvitationDeclined(String username) {
        alertMessage.shouldHave(text(String.format(DECLINE_FRIEND_INVITATION_MESSAGE, username)));
        return this;
    }

    @Step("Decline the {0}'s friend invitation")
    public PeopleAroundComponent declineInvitation(String username) {
        findUser(username).find(String.format("button[aria-label='%s']", DECLINE_INVITATION.text)).click();
        return this;
    }

    @Step("Confirm declining the friend invitation")
    public PeopleAroundComponent confirmDecliningFriendInvitation() {
        confirmDeclineBtn.shouldHave(text("Decline")).click();
        return this;
    }
}
