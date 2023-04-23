package com.rangiffler.api.service;

import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import io.qameta.allure.Step;

import java.util.List;

import static com.rangiffler.config.AppConfig.config;

public class UserdataClient extends RestService {

    public UserdataClient() {
        super(config.userdataUrl());
    }

    private final UserdataService userdataApi = retrofit.create(UserdataService.class);

    @Step("Send REST GET('/currentUser') request to userdata service")
    public UserJson getCurrentUser(String username) throws Exception {
        return userdataApi.currentUser(username)
                .execute()
                .body();
    }

    @Step("Send REST POST('/updateUserInfo') request to userdata service")
    public UserJson updateUser(UserJson userJson) throws Exception {
        return userdataApi.updateUserInfo(userJson)
                .execute()
                .body();
    }

    @Step("Send REST GET('/allUsers') request to userdata service")
    public List<UserJson> allUsers(String username) throws Exception {
        return userdataApi.allUsers(username)
                .execute()
                .body();
    }

    @Step("Send REST POST('/users/invite') request to userdata service")
    public void addFriend(String username, FriendJson friend) throws Exception {
        userdataApi.addFriend(username, friend)
                .execute();
    }

    @Step("Send REST POST('/acceptInvitation') request to userdata service")
    public List<UserJson> acceptInvitation(String username, FriendJson invitation) throws Exception {
        return userdataApi.acceptInvitation(username, invitation)
                .execute()
                .body();
    }
}
