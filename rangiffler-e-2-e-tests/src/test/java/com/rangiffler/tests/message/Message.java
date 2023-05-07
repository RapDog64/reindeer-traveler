package com.rangiffler.tests.message;

public interface Message {

    String BAD_CREDENTIALS = "Bad credentials";
    String PASSWORDS_SHOULD_BE_EQUAL = "Passwords should be equal";
    String ALLOWED_PASSWORD_ERROR_MSG = "Allowed password length should be from 3 to 12 characters";
    String USER_ALREADY_REGISTERED = "Username `%s` already exists";


    String NO_FRIENDS_YET = "No friends yet";
    String INVITATION_SENT_MESSAGE = "Invitation to user %s is sent";
    String DECLINE_FRIEND_INVITATION_MESSAGE = "You declined invitation from user %s";
    String ACCEPT_FRIEND_INVITATION_MESSAGE = "User %s added to your friends";


    String PHOTO_DELETED = "Photo deleted";
    String DIFFERENT_IDS_PROVIDED = "Photo id in url %s is different from Photo id in body %s";
    String PHOTO_NOT_FOUND = "Photo with id %s is not found.";


}
