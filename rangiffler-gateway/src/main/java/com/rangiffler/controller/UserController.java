package com.rangiffler.controller;

import com.rangiffler.model.UserJson;
import com.rangiffler.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Using for working with users")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Receive all users",
            description = "Receive all users",
            tags = {"User Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users received successfully")
    })
    public List<UserJson> receivePeopleAround(@AuthenticationPrincipal Jwt principal) {
        String usernameFromJWT = principal.getClaim("sub");
        return userService.receivePeopleAround(usernameFromJWT);
    }

    @GetMapping("/currentUser")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Receive information a about current user",
            description = "Receive the user's profile of the current user",
            tags = {"User Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User information received successfully")
    })
    public UserJson getCurrentUser(@AuthenticationPrincipal Jwt principal) {
        String usernameFromJWT = principal.getClaim("sub");
        return userService.getCurrentUser(usernameFromJWT);
    }

    @PutMapping("/updateUserInfo")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update the user's information",
            description = "Update the user's profile information",
            tags = {"User Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User information received successfully")
    })
    public UserJson updateCurrentUser(@AuthenticationPrincipal Jwt principal, @RequestBody UserJson user) {
        String usernameFromJWT = principal.getClaim("sub");
        user.setUsername(usernameFromJWT);
        return userService.updateUserInfo(user);
    }
}
