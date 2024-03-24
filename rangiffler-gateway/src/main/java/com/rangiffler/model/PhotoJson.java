package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import com.google.protobuf.NullValue;
import com.rangiffler.grpc.NullableDescription;
import com.rangiffler.grpc.NullableId;
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
            .setId(checkIdValue(photoJson))
            .setCountryId(String.valueOf(photoJson.getCountryJson().getId()))
            .setImage(photoJson.getPhoto())
            .setDescription(checkDescriptionValue(photoJson))
            .setUsername(photoJson.getUsername())
            .build();
  }

  public static PhotoJson toPhotoJson(Photo photo, CountryJson countryJson) {
    return PhotoJson.builder()
            .id(UUID.fromString(photo.getId().getId()))
            .photo(photo.getImage())
            .countryJson(countryJson)
            .description(photo.getDescription().getDescription())
            .username(photo.getUsername())
            .build();
  }

  private static NullableDescription checkDescriptionValue(PhotoJson entity) {
    return entity.getDescription() == null ? NullableDescription.newBuilder().setNull(NullValue.NULL_VALUE).build()
            : NullableDescription.newBuilder().setDescription(entity.getDescription()).build();
  }

  private static NullableId checkIdValue(PhotoJson entity) {
    return entity.getId() == null ? NullableId.newBuilder().setNull(NullValue.NULL_VALUE).build()
            : NullableId.newBuilder().setId(String.valueOf(entity.getId())).build();
  }

}
