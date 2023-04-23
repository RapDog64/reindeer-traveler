package com.rangiffler.page.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.rangiffler.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PeopleAroundComponent extends BasePage<PeopleAroundComponent> {

    private final SelenideElement self = $("tbody tr");
    private final ElementsCollection usernames = $$(testId("person-username"));


    @Step("Verify '{0}' is displayed in the list")
    public PeopleAroundComponent verifyIsPresent(String username) {
        findUser(username).shouldHave(text(username));
        return this;
    }

    @Override
    public PeopleAroundComponent waitForPageLoaded() {
         $(testId(TabItem.PEOPLE_AROUND.name)).shouldBe(visible);
         return this;
    }


    @Step("Search for user '{0}'")
    private SelenideElement findUser(String username) {
        return usernames.find(text(username));
    }

    @Step("Send friendship invitation to '{0}'")
    public PeopleAroundComponent sendInvitation(String username) {
        findUser(username).find("td[test-id='people-add']", 3).click();
        Selenide.refresh();
        return this;
    }

    @Step("verify the invitation message is displayed")
    public void verifyInvitationSent(String username) {
        findUser(username).find("td[test-id='people-add']", 3).shouldHave(text("Invitation sent"));
    }
}
