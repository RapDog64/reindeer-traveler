package com.rangiffler.service;

import com.rangiffler.model.UserJson;
import com.rangiffler.service.configuration.UserServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserServiceClient userService;

    public UserService(UserServiceClient userService) {
        this.userService = userService;
    }

    public List<UserJson> receivePeopleAround(String username) {
        return userService.receivePeopleAround(username);
    }

    public UserJson getCurrentUser(String username) {
        return userService.currentUser(username);
    }

    public UserJson updateUserInfo(UserJson user) {
        return userService.updateUserInfo(user);
    }

}
