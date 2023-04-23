package com.rangiffler.tests.web.message;

public interface Message {
    String BAD_CREDENTIALS = "Bad credentials";
    String PASSWORDS_SHOULD_BE_EQUAL = "Passwords should be equal";
    String ALLOWED_PASSWORD_ERROR_MSG = "Allowed password length should be from 3 to 12 characters";
    String USER_ALREADY_REGISTERED = "Username `%s` already exists";

    String NO_FRIENDS_YET = "No friends yet";
}
