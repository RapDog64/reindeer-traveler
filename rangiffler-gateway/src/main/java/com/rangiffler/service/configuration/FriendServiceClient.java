package com.rangiffler.service.configuration;

import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import org.springframework.cloud.openfeign.FeignClient;
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

    @GetMapping("/invitations")
    List<UserJson> getInvitations(@RequestParam String username);

    @PostMapping("/acceptInvitation")
    List<UserJson>  acceptInvitation(@RequestParam String username, @RequestBody FriendJson invitation) ;

    @PostMapping("/declineInvitation")
    List<UserJson>  declineInvitation(@RequestParam String username, @RequestBody FriendJson invitation);

    @PostMapping("/addFriend")
    void addFriend(@RequestParam String username, @RequestBody FriendJson friend);

    @DeleteMapping("/removeFriend")
    List<UserJson>  removeFriend(@RequestParam String username, @RequestParam String friendUsername);
}
