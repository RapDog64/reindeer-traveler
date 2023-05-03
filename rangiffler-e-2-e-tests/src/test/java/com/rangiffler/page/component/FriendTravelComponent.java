package com.rangiffler.page.component;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.page.component.TabItem.FRIENDS_TRAVEL;

public class FriendTravelComponent extends BaseComponent<FriendTravelComponent> {

    @Step("Check that page is loaded")
    @Override
    public FriendTravelComponent waitForPageLoaded() {
        $(testId(FRIENDS_TRAVEL.name)).shouldBe(visible);
        return null;
    }
}
