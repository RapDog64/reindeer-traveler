package com.rangiffler.exception;

public class PhotoNotFoundException extends IllegalArgumentException {

    public PhotoNotFoundException(String message) {
        super(message);
    }
}
