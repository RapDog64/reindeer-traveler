package com.rangiffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage<T extends BasePage> {

    private final SelenideElement alertMessage = $("div[role='alert']");

    protected abstract T waitForPageLoaded();

    protected static String testId(String value) {
        return String.format("[test-id='%s']", value);
    }

    protected static String dataTestId(String value) {
        return String.format("[data-testid='%s']", value);
    }


    protected void verifyMessage(String message) {
        alertMessage.shouldHave(Condition.text(message));
    }
}
