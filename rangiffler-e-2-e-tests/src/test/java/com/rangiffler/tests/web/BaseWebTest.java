package com.rangiffler.tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.rangiffler.config.AppConfig;
import com.rangiffler.jupiter.annotation.WebTest;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@WebTest
public class BaseWebTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = AppConfig.config.frontUrl();
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(false));
    }
}
