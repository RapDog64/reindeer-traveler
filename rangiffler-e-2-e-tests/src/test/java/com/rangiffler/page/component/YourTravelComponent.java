package com.rangiffler.page.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.model.enums.Country;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.rangiffler.page.BasePage.dataTestId;
import static com.rangiffler.page.BasePage.testId;
import static com.rangiffler.page.component.TabItem.YOUR_TRAVELS;

public class YourTravelComponent extends BaseComponent<YourTravelComponent> {

    private final ElementsCollection travels = $$("div > ul > li");
    private final SelenideElement closeIcon = $(dataTestId("CloseIcon"));
    private final SelenideElement editIconBtn = $(dataTestId("EditIcon"));
    private final SelenideElement confirmBtn = $("button[type='submit']");
    private final SelenideElement countryText = $(dataTestId("PlaceIcon")).parent();

    private final SelenideElement deleteCartIcon = $(dataTestId("DeleteOutlineIcon")).parent();


    @Override
    @Step("Check that page is loaded")
    public YourTravelComponent waitForPageLoaded() {
        $(testId(YOUR_TRAVELS.name)).shouldBe(visible);
        return this;
    }

    @Step("Remove the travel card")
    public YourTravelComponent removePhoto() {
        deleteCartIcon.click();
        return this;
    }

    @Step("Search for the travel card for '{0.name}'")
    public YourTravelComponent openTravelCard(Country country) {
        travels.find(text(country.name)).click();
        return this;
    }

    @Step("Confirm removing the card")
    public YourTravelComponent confirmRemoving() {
        confirmBtn.click();
        return this;
    }

    @Step("Verify the travel card has been removed")
    public YourTravelComponent verifyTravelRemoved(String message) {
        super.verifyMessage(message);
        travels.shouldHave(size(0));
        return this;
    }

    @Step("Decline removing the card")
    public YourTravelComponent declineConfirmation() {
        closeIcon.click();
        return this;
    }

    @Step("Click 'Edit' card button")
    public YourTravelComponent editTravelCard() {
        editIconBtn.click();
        return this;
    }


    @Step("Save travel card")
    public YourTravelComponent saveTravelCard() {
        confirmBtn.click();
        return this;
    }

    @Step("Verify the travel card has been updated")
    public YourTravelComponent verifyTravelCard(Country country, String description) {
        countryText.shouldHave(text(country.name));
        countryText.sibling(0).shouldHave(text(description));
        return this;
    }

    @Step("Verify the 'Save' button is disabled")
    public AddTravelForm verifySaveButtonIsDisabled() {
        confirmBtn.shouldBe(disabled.because("The user has not upload photo"));
        return new AddTravelForm();
    }
}
