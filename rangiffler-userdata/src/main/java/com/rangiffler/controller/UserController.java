package com.rangiffler.controller;


import com.rangiffler.model.UserJson;
import com.rangiffler.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserDataService userService;

    @PutMapping(value = "/updateUserInfo")
    @ResponseStatus(HttpStatus.OK)
    public UserJson updateUserInfo(@RequestBody UserJson user) {
        return userService.update(user);
    }

    @GetMapping("/currentUser")
    @ResponseStatus(HttpStatus.OK)
    public UserJson currentUser(@RequestParam String username) {
        return userService.getCurrentUser(username);
    }

    @GetMapping("/allUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> receivePeopleAround(@RequestParam String username) {
        return userService.receivePeopleAround(username);
    }
}
