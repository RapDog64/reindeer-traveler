package com.rangiffler.page.component;

import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.page.component.TabItem.*;

public class YourTravelComponent extends BasePage<YourTravelComponent> {

    @Step("Check that page is loaded")
    @Override
    public YourTravelComponent waitForPageLoaded() {
        $(testId(YOUR_TRAVELS.name)).shouldBe(visible);
        return this;
    }
}
