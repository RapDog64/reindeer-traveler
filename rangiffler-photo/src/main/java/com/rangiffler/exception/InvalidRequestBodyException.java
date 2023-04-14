package com.rangiffler.exception;

public class InvalidRequestBodyException extends IllegalArgumentException {

    public InvalidRequestBodyException(String message) {
        super(message);
    }
}
