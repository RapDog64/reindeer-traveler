package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rangiffler.data.UserEntity;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public class UserJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("firstName")
    private String firstname;
    @JsonProperty("lastName")
    private String lastname;
    @JsonProperty("avatar")
    private String photo;
    @JsonProperty("friendState")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FriendState friendState;

    public UserJson() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public FriendState getFriendState() {
        return friendState;
    }

    public void setFriendState(FriendState friendState) {
        this.friendState = friendState;
    }

    public static UserJson fromEntity(UserEntity entity) {
        UserJson usr = new UserJson();
        byte[] photo = entity.getPhoto();
        usr.setId(entity.getId());
        usr.setUserName(entity.getUsername());
        usr.setFirstname(entity.getFirstname());
        usr.setLastname(entity.getLastname());
        usr.setPhoto(photo != null && photo.length > 0 ? new String(entity.getPhoto(), StandardCharsets.UTF_8) : null);
        return usr;
    }

    public static UserJson fromEntity(UserEntity entity, FriendState friendState) {
        UserJson userJson = fromEntity(entity);
        userJson.setFriendState(friendState);
        return userJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserJson userJson = (UserJson) o;
        return Objects.equals(id, userJson.id) && Objects.equals(userName, userJson.userName) && Objects.equals(firstname, userJson.firstname) && Objects.equals(lastname, userJson.lastname) && Objects.equals(photo, userJson.photo) && friendState == userJson.friendState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstname, lastname, photo, friendState);
    }
}
