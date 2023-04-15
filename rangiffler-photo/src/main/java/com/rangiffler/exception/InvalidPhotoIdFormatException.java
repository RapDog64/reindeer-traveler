package com.rangiffler.exception;

public class InvalidPhotoIdFormatException extends IllegalArgumentException {

    public InvalidPhotoIdFormatException(String message) {
        super(message);
    }
}
