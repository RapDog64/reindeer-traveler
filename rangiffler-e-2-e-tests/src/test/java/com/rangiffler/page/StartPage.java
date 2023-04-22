package com.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import com.rangiffler.config.AppConfig;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class StartPage extends BasePage<StartPage> {

    public static final String URL = "/";
    private final SelenideElement loginButton = $("a[href*='redirect']");
    private final SelenideElement registerButton = $("a[href*='register']");
    private final SelenideElement formTitle = $("div > h1");


    @Step("Redirect to login page")
    public LoginPage doLogin() {
        loginButton.click();
        return new LoginPage();
    }

    @Step("Redirect to register page")
    public RegisterPage doRegister() {
        registerButton.click();
        return new RegisterPage();
    }

    @Step("Check that page is loaded")
    @Override
    public StartPage waitForPageLoaded() {
        loginButton.should(visible);
        registerButton.should(visible);
        return this;
    }

    @Step("Check that form title is '{0}'")
    public StartPage checkFormTitle(String title) {
        formTitle.shouldHave(text(title));
        return this;
    }
}
