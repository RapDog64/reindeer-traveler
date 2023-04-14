package com.rangiffler.exception;

public class InvalidCountryIdException extends  IllegalArgumentException {

    public InvalidCountryIdException(String message) {
        super(message);
    }
}
