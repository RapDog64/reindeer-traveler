package com.rangiffler.model.enums;

public enum Country {
    GERMANY("Germany","DE"),
    RUSSIA("Russia","RU"),
    KAZAKHSTAN("Kazakhstan", "KZ");

    public final String name;
    public final String code;

    Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
