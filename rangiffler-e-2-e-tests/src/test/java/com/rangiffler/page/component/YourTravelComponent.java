package com.rangiffler.page.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.model.enums.Country;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.rangiffler.page.component.TabItem.YOUR_TRAVELS;

public class YourTravelComponent extends BasePage<YourTravelComponent> {

    private final ElementsCollection travels = $$("div > ul > li");
    private final SelenideElement confirmRemoveBtn = $("button[type='submit']");
    private final SelenideElement deleteCartIcon = $(dataTestId("DeleteOutlineIcon")).parent();
    private final SelenideElement closeIcon = $(dataTestId("CloseIcon"));

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
        travels.find(Condition.text(country.name)).click();
        return this;
    }

    @Step("Confirm removing the card")
    public YourTravelComponent confirmRemoving() {
        confirmRemoveBtn.click();
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
}
