package com.rangiffler.service;

import com.rangiffler.data.FriendsEntity;
import com.rangiffler.data.UserEntity;
import com.rangiffler.data.repository.UserRepository;
import com.rangiffler.model.FriendJson;
import com.rangiffler.model.FriendState;
import com.rangiffler.model.UserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserDataService {

    private final UserRepository userRepository;

    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserJson update(UserJson user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUserName());
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setPhoto(user.getPhoto() != null ? user.getPhoto().getBytes(StandardCharsets.UTF_8) : null);
        UserEntity saved = userRepository.save(userEntity);

        return UserJson.fromEntity(saved);
    }

    public UserJson getCurrentUserOrCreateIfAbsent(String username) {
        UserEntity userDataEntity = userRepository.findByUsername(username);
        if (userDataEntity == null) {
            userDataEntity = new UserEntity();
            userDataEntity.setUsername(username);
            return UserJson.fromEntity(userRepository.save(userDataEntity));
        } else {
            return UserJson.fromEntity(userDataEntity);
        }
    }

    public List<UserJson> receivePeopleAround(String username) {
        Map<UUID, UserJson> result = new HashMap<>();
        for (UserEntity user : userRepository.findByUsernameNot(username)) {
            List<FriendsEntity> sendInvites = user.getFriends();
            List<FriendsEntity> receivedInvites = user.getInvites();

            if (sendInvites.isEmpty() && receivedInvites.isEmpty()) {
                result.put(user.getId(), UserJson.fromEntity(user, FriendState.NOT_FRIEND));
            }

            if (!sendInvites.isEmpty() || !receivedInvites.isEmpty()) {
                Optional<FriendsEntity> inviteToMe = sendInvites.stream()
                        .filter(invite -> invite.getFriend().getUsername().equals(username))
                        .findFirst();

                Optional<FriendsEntity> inviteFromMe = receivedInvites.stream()
                        .filter(invite -> invite.getUser().getUsername().equals(username))
                        .findFirst();

                if (inviteToMe.isPresent()) {
                    FriendsEntity invite = inviteToMe.get();
                    result.put(user.getId(), UserJson.fromEntity(user, invite.isPending()
                            ? FriendState.INVITATION_RECEIVED
                            : FriendState.FRIEND));
                }
                if (inviteFromMe.isPresent()) {
                    FriendsEntity invite = inviteFromMe.get();
                    result.put(user.getId(), UserJson.fromEntity(user, invite.isPending()
                            ? FriendState.INVITATION_SENT
                            : FriendState.FRIEND));
                }
            }
            if (!result.containsKey(user.getId())) {
                result.put(user.getId(), UserJson.fromEntity(user));
            }
        }
        return new ArrayList<>(result.values());
    }

    public List<UserJson> friends(String username, boolean includePending) {
        return userRepository.findByUsername(username)
                .getFriends()
                .stream()
                .filter(friend -> includePending || !friend.isPending())
                .map(friend -> UserJson.fromEntity(friend.getFriend(), friend.isPending()
                        ? FriendState.INVITATION_SENT
                        : FriendState.FRIEND))
                .toList();
    }

    public void sendFriendshipInvitation(String username, FriendJson friend) {
        UserEntity currentUser = userRepository.findByUsername(username);
        currentUser.addFriends(true, userRepository.findByUsername(friend.getUsername()));
        userRepository.save(currentUser);
    }

    public List<UserJson> acceptInvitation(String username, FriendJson invitation) {
        UserEntity currentUser = userRepository.findByUsername(username);
        UserEntity inviteUser = userRepository.findByUsername(invitation.getUsername());

        FriendsEntity invite = currentUser.getInvites()
                .stream()
                .filter(friend -> friend.getUser().equals(inviteUser))
                .findFirst()
                .orElseThrow();

        invite.setPending(false);
        currentUser.addFriends(false, inviteUser);
        userRepository.save(currentUser);

        return currentUser
                .getFriends()
                .stream()
                .map(friend -> UserJson.fromEntity(friend.getFriend(), friend.isPending()
                        ? FriendState.INVITATION_SENT
                        : FriendState.FRIEND))
                .toList();
    }

    public List<UserJson> declineInvitation(String username, FriendJson invitation) {
        UserEntity currentUser = userRepository.findByUsername(username);
        UserEntity friendToDecline = userRepository.findByUsername(invitation.getUsername());
        currentUser.removeInvites(friendToDecline);
        currentUser.removeFriends(friendToDecline);
        userRepository.save(currentUser);
        return currentUser.getInvites()
                .stream()
                .filter(FriendsEntity::isPending)
                .map(friendsEntity -> UserJson.fromEntity(friendsEntity.getUser(), FriendState.INVITATION_RECEIVED))
                .toList();
    }

    public List<UserJson> removeFriend(String username, String friendUsername) {
        UserEntity currentUser = userRepository.findByUsername(username);
        UserEntity friendToRemove = userRepository.findByUsername(friendUsername);
        currentUser.removeFriends(friendToRemove);
        currentUser.removeInvites(friendToRemove);
        userRepository.save(currentUser);
        return currentUser
                .getFriends()
                .stream()
                .map(friend -> UserJson.fromEntity(friend.getFriend(), friend.isPending()
                        ? FriendState.INVITATION_SENT
                        : FriendState.FRIEND))
                .toList();
    }
}
