package com.rangiffler.model.enums;

public enum Country {
    GERMANY("DE"),
    RUSSIA("RU"),
    KAZAKHSTAN("KZ");

    public final String code;

    Country(String code) {
        this.code = code;
    }
}
