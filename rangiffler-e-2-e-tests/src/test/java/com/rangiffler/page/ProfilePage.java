package com.rangiffler.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage<ProfilePage> {

    private final SelenideElement firstnameInput = $(byTestId("firstname")).$("input");
    private final SelenideElement lastnameInput = $(byTestId("lastname")).$("input");
    private final SelenideElement saveBtn = $("[type='submit']");

    @Override
    public ProfilePage waitForPageLoaded() {
        return null;
    }

    @Step("Type firstname '{0}'")
    public ProfilePage typeFirstname(String firstname) {
        firstnameInput.setValue(firstname);
        return this;
    }

    @Step("Type lastname '{0}'")
    public ProfilePage typeLastname(String lastname) {
        lastnameInput.setValue(lastname);
        return this;
    }

    @Step("Save changes")
    public MainPage save() {
        saveBtn.click();
        return new MainPage();
    }

    @Step("Verify the user's has updated to {0} and {1}")
    public void validateUserProfile(String firstname, String lastname) {
        // TODO: Create a custom selenide condition
        firstnameInput.shouldHave(value(firstname));
        lastnameInput.shouldHave(value(lastname));
    }
}
