package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import com.google.protobuf.NullValue;
import com.rangiffler.grpc.NullableString;
import com.rangiffler.grpc.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PhotoJson {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("country")
  private CountryJson countryJson;

  @JsonProperty("photo")
  private String photo;

  @JsonProperty("description")
  private String description;

  @JsonProperty("username")
  private String username;

  public PhotoJson() {
  }

  public static Photo toPhotoGrpc(PhotoJson photoJson) {
    return Photo.newBuilder()
            .setId(String.valueOf(photoJson.getId()))
            .setCountryId(String.valueOf(photoJson.getCountryJson().getId()))
            .setPhoto(photoJson.getPhoto())
            .setDescription(checkDescriptionValue(photoJson))
            .setUsername(photoJson.getUsername())
            .build();
  }

  public static PhotoJson toPhotoJson(Photo photo, CountryJson countryJson) {
    return PhotoJson.builder()
            .id(UUID.fromString(photo.getId()))
            .photo(photo.getPhoto())
            .countryJson(countryJson)
            .description(photo.getDescription().getDescription())
            .username(photo.getUsername())
            .build();
  }

  private static NullableString checkDescriptionValue(PhotoJson entity) {
    return entity.getDescription() == null ? NullableString.newBuilder().setNull(NullValue.NULL_VALUE).build()
            : NullableString.newBuilder().setDescription(entity.getDescription()).build();
  }
}
