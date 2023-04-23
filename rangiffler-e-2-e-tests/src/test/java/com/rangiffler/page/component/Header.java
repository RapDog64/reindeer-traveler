package com.rangiffler.page.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.page.BasePage.testId;

public class Header {

    private final SelenideElement header = $("header");
    private final SelenideElement amountOfFriends = $(testId("friends-icon"));

    public SelenideElement getHeader() {
        return header;
    }

    @Step("Click on '{0}'")
    public <T extends BasePage> T clickOn(PanelAttribute attribute, T expectedPage ) {
        $(testId(attribute.name)).click();
        return expectedPage;
    }

    @Step("Verify amount of friends at the header panel")
    public void verifyAmountFriends(int size) {
        amountOfFriends.shouldHave(Condition.text(String.valueOf(size)));
    }
}
