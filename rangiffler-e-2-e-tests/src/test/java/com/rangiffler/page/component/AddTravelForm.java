package com.rangiffler.page.component;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.page.BasePage.testId;

public class AddTravelForm extends BaseComponent<AddTravelForm> {

    @Override
    @Step("Wait for the page loaded")
    public AddTravelForm waitForPageLoaded() {
        $(testId("add-travel")).shouldBe(appear);
        return this;
    }
}
