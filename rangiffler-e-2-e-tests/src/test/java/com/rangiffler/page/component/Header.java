package com.rangiffler.page.component;

import com.codeborne.selenide.SelenideElement;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.rangiffler.page.BasePage.byTestId;

public class Header {

    private final SelenideElement header = $("header");

    public SelenideElement getHeader() {
        return header;
    }

    @Step("Click on '{0}'")
    public <T extends BasePage> T clickOn(PanelAttribute attribute, T expectedPage ) {
        $(byTestId(attribute.name)).click();
        return expectedPage;
    }
}
