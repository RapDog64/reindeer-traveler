package com.rangiffler.page;

public abstract class BasePage<T extends BasePage> {

    public abstract T waitForPageLoaded();


    public static String byTestId(String value) {
        return String.format("[test-id='%s']", value);
    }
}
