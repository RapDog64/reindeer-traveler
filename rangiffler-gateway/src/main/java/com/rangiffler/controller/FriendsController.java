package com.rangiffler.controller;


import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.service.configuration.FriendServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendsController {

    private final FriendServiceClient friendService;

    @Autowired
    public FriendsController(FriendServiceClient friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/friends")
    public List<UserJson> getFriendsByUserId(@AuthenticationPrincipal Jwt principal, @RequestParam boolean includePending) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.getFriends(usernameFromJWT, includePending);
    }

    @PostMapping("users/invite/")
    public UserJson sendInvitation(@AuthenticationPrincipal Jwt principal, @RequestBody UserJson user) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.sendInvitation(usernameFromAuth, user);
    }

    @PostMapping("friends/remove")
    public List<UserJson> removeFriendFromUser(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.removeFriend(usernameFromJWT, friend.getUsername());
    }

    @PostMapping("friends/submit")
    public List<UserJson> submitFriend(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.acceptInvitation(usernameFromJWT, friend);
    }

    @PostMapping("friends/decline")
    public List<UserJson> declineFriend(@AuthenticationPrincipal Jwt principal, @RequestBody FriendJson friend) {
        String usernameFromJWT = principal.getClaim("sub");
        return friendService.declineInvitation(usernameFromJWT, friend);
    }
}
