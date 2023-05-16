package com.rangiffler.jupiter.extension;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static io.qameta.allure.Allure.step;

public class BrowserExtension implements TestExecutionExceptionHandler, AfterEachCallback {

    @Override
    public void handleTestExecutionException(final ExtensionContext context, Throwable throwable) throws Throwable {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("Screenshot on fail",
                    new ByteArrayInputStream(((TakesScreenshot) WebDriverRunner.getWebDriver())
                            .getScreenshotAs(OutputType.BYTES)));
        }
        throw throwable;
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        step("Close the browser", () -> {
            if (WebDriverRunner.hasWebDriverStarted()) {
                Selenide.closeWebDriver();
            }
        });
    }
}
