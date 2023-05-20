package com.rangiffler.controller;

import com.rangiffler.model.UserJson;
import com.rangiffler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserJson> receivePeopleAround(@AuthenticationPrincipal Jwt principal) {
        String usernameFromJWT = principal.getClaim("sub");
        return userService.receivePeopleAround(usernameFromJWT);
    }

    @GetMapping("/currentUser")
    public UserJson getCurrentUser(@AuthenticationPrincipal Jwt principal) {
        String usernameFromJWT = principal.getClaim("sub");
        return userService.getCurrentUser(usernameFromJWT);
    }

    @PutMapping("/updateUserInfo")
    public UserJson updateCurrentUser(@AuthenticationPrincipal Jwt principal, @RequestBody UserJson user) {
        String usernameFromJWT = principal.getClaim("sub");
        user.setUsername(usernameFromJWT);
        return userService.updateUserInfo(user);
    }
}
