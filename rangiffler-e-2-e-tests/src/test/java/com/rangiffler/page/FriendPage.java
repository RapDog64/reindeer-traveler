package com.rangiffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.model.UserJson;
import com.rangiffler.tests.message.Message;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.condition.FriendsCondition.friends;

public class FriendPage extends BasePage<FriendPage> {

    private final SelenideElement self = $(testId("friends-list"));
    private final ElementsCollection friendsList = self.$$(testId("friend-card"));
    private final SelenideElement closeFriendIcon = $(testId("close-icon"));
    private final SelenideElement emptyListMessage = $(testId("empty-friends-list"));

    @Step("Verify the user has {0} amount of the added friends")
    public FriendPage verifyFriendList(int count) {
        friendsList.shouldHave(size(count));
        return this;
    }

    @Step("Verify the user's friend list is empty")
    public FriendPage verifyFriendListIsEmpty() {
        emptyListMessage.shouldHave(text(Message.NO_FRIENDS_YET));
        return this;
    }

    @Step("Verify the friend is successfully added")
    public FriendPage verifyFriendAdded(UserJson... expectedFriends) {
        // TODO: Finished custom condition validation
        friendsList.should(friends(expectedFriends));
        return this;
    }

    @Override
    @Step("Wait for Friend page is loaded")
    public FriendPage waitForPageLoaded() {
        closeFriendIcon.shouldBe(visible);
        return this;
    }
}
