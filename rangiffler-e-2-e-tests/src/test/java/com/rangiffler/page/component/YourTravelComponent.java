package com.rangiffler.page.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.model.enums.Country;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.rangiffler.page.component.TabItem.YOUR_TRAVELS;
import static org.openqa.selenium.By.ByCssSelector.cssSelector;

public class YourTravelComponent extends BasePage<YourTravelComponent> {

    private final ElementsCollection countryList = $$("ul > li");
    private final ElementsCollection travels = $$("div > ul > li");
    private final SelenideElement closeIcon = $(dataTestId("CloseIcon"));
    private final SelenideElement editIconBtn = $(dataTestId("EditIcon"));
    private final SelenideElement confirmRemoveBtn = $("button[type='submit']");
    private final SelenideElement countryText = $(dataTestId("PlaceIcon")).parent();
    private final SelenideElement travelDescriptionTextArea = $("div > textarea");
    private final SelenideElement deleteCartIcon = $(dataTestId("DeleteOutlineIcon")).parent();
    private final SelenideElement openDropDownBtn = $(dataTestId("ArrowDropDownIcon")).parent();

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

    @Step("Click 'Edit' card button")
    public YourTravelComponent editTravelCard() {
        editIconBtn.click();
        return this;
    }

    @Step("Fill {0.name} and description: {1}")
    public YourTravelComponent fillTravelCard(Country country, String description) {
        selectCountry(country);
        clear("div > textarea");
        travelDescriptionTextArea.setValue(description);
        return this;
    }

    @Step("Select {0.name}")
    public YourTravelComponent selectCountry(Country country) {
        openDropDownBtn.click();
        countryList.find(attribute("data-value", country.code)).click();
        return this;
    }

    @Step("Save travel card")
    public YourTravelComponent saveTravelCard() {
        confirmRemoveBtn.click();
        return this;
    }

    @Step("Verify the travel card has been updated")
    public YourTravelComponent verifyTravelCard(Country country, String description) {
        countryText.shouldHave(text(country.name));
        countryText.sibling(0).shouldHave(text(description));
        return this;
    }


    @Step("Remove old description")
    private void clear(String selector) {
        WebElement element = getWebDriver().findElement(cssSelector(selector));
        element.sendKeys(Keys.COMMAND + "A");
        element.sendKeys(Keys.DELETE);
    }
}
