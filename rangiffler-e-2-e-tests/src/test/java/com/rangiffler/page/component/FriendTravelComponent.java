package com.rangiffler.page.component;

import com.rangiffler.page.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.page.component.TabItem.*;

public class FriendTravelComponent extends BasePage<FriendTravelComponent> {

    @Override
    public FriendTravelComponent waitForPageLoaded() {
        $(testId(FRIENDS_TRAVEL.name)).shouldBe(visible);
        return null;
    }
}
