package com.rangiffler.page;

import com.rangiffler.page.component.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MainPage extends BasePage<MainPage> {

    protected final Header header = new Header();

    @Override
    public MainPage waitForPageLoaded() {
        header.getHeader().should(visible).$("div > h1").shouldHave(text("Rangiffler"));
        return this;
    }
}
