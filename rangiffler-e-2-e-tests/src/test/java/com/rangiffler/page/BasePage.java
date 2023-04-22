package com.rangiffler.page;

public abstract class BasePage<T extends BasePage> {

    public abstract T waitForPageLoaded();


    public static String testId(String value) {
        return String.format("[test-id='%s']", value);
    }

    public static String testIdXpath(String value) {
        return String.format("//*[@test-id='%s']", value);
    }
}
