package com.rangiffler.exception;

public interface ErrorMessages {

    String PHOTO_NOT_FOUND = "Photo with id %s is not found.";
    String INVALID_REQUEST_BODY = "Invalid request body.";
    String INVALID_USERNAME = "Invalid username.";
    String INVALID_PHOTO_ID_FORMAT = "Invalid photo id format. Photo id must be in UUID format.";
    String DIFFERENT_IDS_PROVIDED = "Photo id in url %s is different from Photo id in body %s";
}
