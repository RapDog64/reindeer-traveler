package com.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class StartPage extends BasePage<StartPage> {

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

    @Override
    @Step("Check that page is loaded")
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
