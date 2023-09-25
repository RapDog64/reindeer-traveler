package com.rangiffler.controller;


import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Friend Controller", description = "Using for working with friends")
public class FriendsController {

    private final FriendService friendService;

    @GetMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Receive the user's friend",
            description = "Receive the user's friend",
            tags = {"Friend Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Received friend successfully")
    })
    public List<UserJson> getFriendsByUserId(@AuthenticationPrincipal Jwt principal, @RequestParam boolean includePending) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.getFriends(usernameFromJWT, includePending);
    }

    @PostMapping("users/invite/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Send a friendship invitation",
            description = "Send a friendship invitation to a user",
            tags = {"Friend Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Invitation sent successfully")
    })
    public UserJson sendInvitation(@AuthenticationPrincipal Jwt principal, @RequestBody UserJson user) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.sendInvitation(usernameFromAuth, user);
    }

    @PostMapping("friends/remove")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Remove a friend",
            description = "Remove a friend",
            tags = {"Friend Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Remove friend from the user's list")
    })
    public List<UserJson> removeFriendFromUser(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.removeUserFromFriends(usernameFromJWT, friend.getUsername());
    }

    @PostMapping("friends/submit")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Submit the friendship invitation",
            description = "Submit the friendship invitation",
            tags = {"Friend Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Submitted successfully")
    })
    public List<UserJson> submitFriend(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.acceptInvitation(usernameFromJWT, friend);
    }

    @PostMapping("friends/decline")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Decline the friendship invitation",
            description = "Decline the friendship invitation",
            tags = {"Friend Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Declined the friendship invitation")
    })
    public List<UserJson> declineFriend(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.declineInvitation(usernameFromJWT, friend);
    }
}
