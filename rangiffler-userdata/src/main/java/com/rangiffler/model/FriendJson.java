package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendJson {

    @JsonProperty("username")
    private String username;

}
