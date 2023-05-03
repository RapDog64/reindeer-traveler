package com.rangiffler.page.component;

public enum HeaderItem {
    ADD_PHOTO("add-travel"),
    USER_PROFILE("user-profile-btn"),
    FRIENDS("friends-icon"),
    LOGOUT("logout-btn");

    public final String name;

    HeaderItem(String name) {
        this.name = name;
    }
}
