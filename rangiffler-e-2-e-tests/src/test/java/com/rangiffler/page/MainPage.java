package com.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import com.rangiffler.config.AppConfig;
import com.rangiffler.page.component.Header;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage<MainPage> {

    public static final String URL = AppConfig.config.frontUrl();
    private final SelenideElement logoutButton = $("[data-testid='LogoutIcon']");
    protected final Header header = new Header();

    @Override
    public MainPage waitForPageLoaded() {
        header.getHeader().should(visible).$("div > h1").shouldHave(text("Rangiffler"));
        return this;
    }

    @Step("Check application name '{0}'")
    public MainPage verifyApplicationName(String name) {
        header.getHeader().should(visible).$("div > h1").shouldHave(text(name));
        return this;
    }

    @Step("Log out from the application")
    public StartPage doLogout() {
        logoutButton.click();
        return new StartPage();
    }
}
