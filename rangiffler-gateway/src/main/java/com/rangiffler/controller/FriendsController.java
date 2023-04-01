package com.rangiffler.controller;


import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.service.UserService;
import com.rangiffler.service.configuration.FriendServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendsController {

    private final FriendServiceClient friendService;

    @Autowired
    public FriendsController(UserService userService, FriendServiceClient friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/friends")
    public List<UserJson> getFriendsByUserId(@AuthenticationPrincipal Jwt principal,
                                             @RequestParam boolean includePending) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.getFriends(usernameFromAuth, includePending);
    }

    @GetMapping("/invitations")
    public List<UserJson> getInvitations(@AuthenticationPrincipal Jwt principal) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.getInvitations(usernameFromAuth);
    }

    @PostMapping("users/invite/")
    public UserJson sendInvitation(@AuthenticationPrincipal Jwt principal,
                                   @RequestBody FriendJson user) {
        String usernameFromAuth = principal.getClaim("sub");

        return null;
//        return friendService.getInvitations(usernameFromAuth);
    }

    @PostMapping("friends/remove")
    public List<UserJson> removeFriendFromUser(@AuthenticationPrincipal Jwt principal,
                                               @RequestParam String friendUsername) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.removeFriend(usernameFromAuth, friendUsername);
    }

    @PostMapping("friends/submit")
    public List<UserJson> submitFriend(@AuthenticationPrincipal Jwt principal,
                                       @RequestBody FriendJson friend) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.acceptInvitation(usernameFromAuth, friend);
    }

    @PostMapping("friends/decline")
    public List<UserJson> declineFriend(@AuthenticationPrincipal Jwt principal,
                                        @RequestBody FriendJson friend) {
        String usernameFromAuth = principal.getClaim("sub");
        return friendService.declineInvitation(usernameFromAuth, friend);
    }
}
