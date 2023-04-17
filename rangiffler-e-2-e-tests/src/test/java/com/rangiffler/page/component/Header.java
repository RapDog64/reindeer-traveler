package com.rangiffler.page.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {

    private final SelenideElement header = $("header");

    public SelenideElement getHeader() {
        return header;
    }
}
