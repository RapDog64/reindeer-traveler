package com.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class StartPage extends BasePage<StartPage> {

    private SelenideElement loginButton = $("");
    private SelenideElement registerButton = $("");

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
}
