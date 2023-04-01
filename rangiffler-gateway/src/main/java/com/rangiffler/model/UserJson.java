package com.rangiffler.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.rangiffler.config.RangifflerGatewayConfig.MAX_PHOTO_SIZE;

@Data
@Builder
public class UserJson {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("firstName")
  private String firstname;

  @JsonProperty("lastName")
  private String lastname;

  @JsonProperty("photo")
  @Size(max = MAX_PHOTO_SIZE)
  private String photo;

  @JsonProperty("friendState")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private FriendStatus friendStatus;
}

