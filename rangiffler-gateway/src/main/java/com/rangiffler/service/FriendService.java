package com.rangiffler.service;

import com.rangiffler.model.FriendJson;
import com.rangiffler.model.UserJson;
import com.rangiffler.service.configuration.FriendServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendService {

    private final FriendServiceClient friendService;

    @Autowired
    public FriendService(FriendServiceClient friendService) {
        this.friendService = friendService;
    }

    public List<UserJson> getInvitations(String username) {
        return friendService.getInvitations(username);
    }

    public List<UserJson> declineInvitation(String username, FriendJson friendInvitation) {
        return friendService.declineInvitation(username, friendInvitation);
    }


    public List<UserJson> removeUserFromFriends(String username, String friendUsername) {
        return friendService.removeFriend(username, friendUsername);
    }


    public List<UserJson> getFriends(String username, boolean includePending) {
        return friendService.getFriends(username, includePending);
    }

    public UserJson sendInvitation(UserJson user) {
//        UserJson userJson = allUsers.stream().filter(u -> u.getId().equals(user.getId())).findFirst()
//                .orElseThrow();
//        userJson.setFriendStatus(FriendStatus.INVITATION_SENT);
        return null;
    }

    public List<UserJson> acceptInvitation(String username, FriendJson friend) {
        return friendService.acceptInvitation(username, friend);
    }
}
