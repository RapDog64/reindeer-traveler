package com.rangiffler.tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.rangiffler.config.AppConfig;
import com.rangiffler.jupiter.annotation.WebTest;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@WebTest
public abstract class BaseWebTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = AppConfig.config.frontUrl();
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 12000;
        Configuration.headless = true;
    }

    @BeforeEach
    @Step("Add allure selenide listener")
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(false));
    }
}
