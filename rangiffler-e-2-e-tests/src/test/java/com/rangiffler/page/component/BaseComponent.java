package com.rangiffler.page.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.model.enums.Country;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.cssSelector;

public abstract class BaseComponent<T extends BaseComponent> extends BasePage {

    private final ElementsCollection countryList = $$("ul > li");
    private final SelenideElement travelDescriptionTextArea = $("div > textarea");
    private final SelenideElement openDropDownBtn = $(dataTestId("ArrowDropDownIcon")).parent();

    @Step("Fill country: '{0.name}' and description: '{1}'")
    public YourTravelComponent fillTravelCard(Country country, String description) {
        selectCountry(country);
        clear("div > textarea");
        travelDescriptionTextArea.setValue(description);
        return new YourTravelComponent();
    }

    @Step("Select '{0.name}' country")
    public YourTravelComponent selectCountry(Country country) {
        openDropDownBtn.click();
        countryList.find(attribute("data-value", country.code)).click();
        return new YourTravelComponent();
    }

    @Step("Remove old description")
    private void clear(String selector) {
        WebElement element = getWebDriver().findElement(cssSelector(selector));
        element.sendKeys(Keys.COMMAND + "A");
        element.sendKeys(Keys.DELETE);
    }

}
