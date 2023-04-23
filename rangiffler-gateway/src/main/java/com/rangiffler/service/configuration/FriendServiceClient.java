package com.rangiffler.service.configuration;

import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "rangiffler-userdata", url = "http://127.0.0.1:8089")
public interface FriendServiceClient {

    @GetMapping("/friends")
    List<UserJson> getFriends(@RequestParam String username, @RequestParam boolean includePending);

    @PostMapping("/acceptInvitation")
    List<UserJson>  acceptInvitation(@RequestParam String username, @RequestBody FriendJson invitation) ;

    @PostMapping("/declineInvitation")
    List<UserJson>  declineInvitation(@RequestParam String username, @RequestBody FriendJson invitation);

    @PostMapping("/addFriend")
    void addFriend(@RequestParam String username, @RequestBody FriendJson friend);

    @PostMapping("friends/remove")
    List<UserJson> removeFriend(@RequestParam String username, @RequestParam String friendUsername);

    @PostMapping("/users/invite")
    UserJson sendInvitation(@RequestParam String username, @RequestBody UserJson user);
}
