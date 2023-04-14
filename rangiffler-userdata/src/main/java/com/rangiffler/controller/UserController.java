package com.rangiffler.controller;


import com.rangiffler.model.UserJson;
import com.rangiffler.service.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserDataService userService;

    @Autowired
    public UserController(UserDataService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/updateUserInfo")
    @ResponseStatus(HttpStatus.OK)
    public UserJson updateUserInfo(@RequestBody UserJson user) {
        return userService.update(user);
    }

    @GetMapping("/currentUser")
    @ResponseStatus(HttpStatus.OK)
    public UserJson currentUser(@RequestParam String username) {
        return userService.getCurrentUserOrCreateIfAbsent(username);
    }

    @GetMapping("/allUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> receivePeopleAround(@RequestParam String username) {
        return userService.receivePeopleAround(username);
    }
}
