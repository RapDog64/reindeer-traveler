package com.rangiffler.page.component;

import com.codeborne.selenide.SelenideElement;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Header extends BasePage<Header> {

    private final SelenideElement header = $("header");
    private final SelenideElement amountOfFriends = $(testId("friends-icon"));

    public SelenideElement getHeader() {
        return header;
    }

    @Step("Click on '{0}'")
    public <T extends BasePage> T clickOn(PanelAttribute attribute, T expectedPage) {
        $(testId(attribute.name)).click();
        return expectedPage;
    }

    @Step("Verify amount of friends at the header panel")
    public void verifyAmountFriends(int size) {
        amountOfFriends.shouldHave(text(String.valueOf(size)));
    }

    @Step("Check that page is loaded")
    @Override
    public Header waitForPageLoaded() {
        header.shouldBe(visible, Duration.ofSeconds(12));
        return this;
    }
}
