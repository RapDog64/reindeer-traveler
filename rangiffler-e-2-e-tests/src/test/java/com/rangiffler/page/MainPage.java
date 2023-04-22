package com.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import com.rangiffler.page.component.Header;
import com.rangiffler.page.component.TabItem;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage<MainPage> {

    public static final String URL = "";
    private final SelenideElement logoutButton = $("[data-testid='LogoutIcon']");
    private final Header header = new Header();


    @Step("Navigate to the header panel")
    public Header getHeader() {
        return header;
    }

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

    public MainPage navigateToTabs() {
        return this;
    }


    @Step("Open '{0}' tab")
    public <T extends BasePage> T openTab(TabItem tab, T expectedPage) {
        $(testId(tab.name)).click();
        return expectedPage;
    }
}
