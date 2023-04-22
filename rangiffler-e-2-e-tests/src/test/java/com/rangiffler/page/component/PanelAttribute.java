package com.rangiffler.page.component;

public enum PanelAttribute {
    USER_PROFILE("user-profile-btn"),
    FRIENDS("friends-icon"),
    LOGOUT("logout-btn");

    public final String name;

    PanelAttribute(String name) {
        this.name = name;
    }
}
