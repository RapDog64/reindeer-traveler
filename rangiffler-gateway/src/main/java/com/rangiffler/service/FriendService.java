package com.rangiffler.service;

import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.service.configuration.FriendServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendServiceClient friendService;

    public List<UserJson> declineInvitation(String username, FriendJson friendInvitation) {
        return friendService.declineInvitation(username, friendInvitation);
    }

    public List<UserJson> removeUserFromFriends(String username, String friendUsername) {
        return friendService.removeFriend(username, friendUsername);
    }


    public List<UserJson> getFriends(String username, boolean includePending) {
        return friendService.getFriends(username, includePending);
    }

    public UserJson sendInvitation(String username, UserJson user) {
        return friendService.sendInvitation(username, user);
    }

    public List<UserJson> acceptInvitation(String username, FriendJson friend) {
        return friendService.acceptInvitation(username, friend);
    }
}
