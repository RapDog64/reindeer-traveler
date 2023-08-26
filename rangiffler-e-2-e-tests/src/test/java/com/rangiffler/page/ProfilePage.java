package com.rangiffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage extends BasePage<ProfilePage> {

    private final SelenideElement firstnameInput = $(testId("firstname")).$("input");
    private final SelenideElement lastnameInput = $(testId("lastname")).$("input");
    private final SelenideElement saveBtn = $("[type='submit']");
    private final SelenideElement closeEditFromBtn = $(testId("CloseIcon"));

    @Override
    @Step("Check that page is loaded")
    public ProfilePage waitForPageLoaded() {
        firstnameInput.shouldBe(visible);
        lastnameInput.shouldBe(visible);
        saveBtn.shouldBe(visible);
        return this;
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
        closeEditFromBtn.shouldBe(disappear);
        return new MainPage();
    }

    @Step("Verify the user's has updated to {0} and {1}")
    public void validateUserProfile(String firstname, String lastname) {
        firstnameInput.shouldHave(value(firstname));
        lastnameInput.shouldHave(value(lastname));
    }

    @Step("Upload a avatar")
    public ProfilePage uploadAvatar(String pathToAvatar) {
        $(".visually-hidden[type=file]").uploadFromClasspath(pathToAvatar);
        return this;
    }
}
