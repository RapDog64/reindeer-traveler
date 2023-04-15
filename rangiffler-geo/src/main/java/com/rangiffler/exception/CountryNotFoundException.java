package com.rangiffler.exception;

public class CountryNotFoundException extends IllegalArgumentException {

    public CountryNotFoundException(String message) {
        super(message);
    }
}
