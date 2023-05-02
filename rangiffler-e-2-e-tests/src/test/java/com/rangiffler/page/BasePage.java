package com.rangiffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage<T extends BasePage> {

    private final SelenideElement alertMessage = $("div[role='alert']");

    public abstract T waitForPageLoaded();

    public static String testId(String value) {
        return String.format("[test-id='%s']", value);
    }

    public static String dataTestId(String value) {
        return String.format("[data-testid='%s']", value);
    }


    protected void verifyMessage(String message) {
        alertMessage.shouldHave(Condition.text(message));
    }
}
