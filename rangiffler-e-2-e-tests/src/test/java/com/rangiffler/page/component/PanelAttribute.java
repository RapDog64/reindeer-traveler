package com.rangiffler.page.component;

public enum PanelAttribute {
    USER_PROFILE("user-profile-btn"),
    FRIENDS(""),
    LOGOUT("");

    public final String name;

    PanelAttribute(String name) {
        this.name = name;
    }
}
