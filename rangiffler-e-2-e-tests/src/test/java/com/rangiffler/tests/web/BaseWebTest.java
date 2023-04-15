package com.rangiffler.tests.web;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.rangiffler.jupiter.annotation.WebTest;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;

@WebTest
public class BaseWebTest {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(false));
    }
}
