package com.rangiffler.page;

public abstract class BasePage<T extends BasePage> {

    public abstract T waitForPageLoaded();
}
