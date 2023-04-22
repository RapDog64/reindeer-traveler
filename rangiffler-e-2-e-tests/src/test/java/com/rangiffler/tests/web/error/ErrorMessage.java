package com.rangiffler.tests.web.error;

public interface ErrorMessage {
    String BAD_CREDENTIALS = "Bad credentials";
    String PASSWORDS_SHOULD_BE_EQUAL = "Passwords should be equal";
    String ALLOWED_PASSWORD_ERROR_MSG = "Allowed password length should be from 3 to 12 characters";
    String USER_ALREADY_REGISTERED = "Username `%s` already exists";
}
