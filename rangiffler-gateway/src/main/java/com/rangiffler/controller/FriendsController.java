package com.rangiffler.controller;


import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.service.FriendService;
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
public class FriendsController {

    private final FriendService friendService;

    @GetMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> getFriendsByUserId(@AuthenticationPrincipal Jwt principal, @RequestParam boolean includePending) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.getFriends(usernameFromJWT, includePending);
    }

    @PostMapping("users/invite/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserJson sendInvitation(@AuthenticationPrincipal Jwt principal, @RequestBody UserJson user) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.sendInvitation(usernameFromAuth, user);
    }

    @PostMapping("friends/remove")
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> removeFriendFromUser(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.removeUserFromFriends(usernameFromJWT, friend.getUsername());
    }

    @PostMapping("friends/submit")
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> submitFriend(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.acceptInvitation(usernameFromJWT, friend);
    }

    @PostMapping("friends/decline")
    @ResponseStatus(HttpStatus.OK)
    public List<UserJson> declineFriend(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.declineInvitation(usernameFromJWT, friend);
    }
}
