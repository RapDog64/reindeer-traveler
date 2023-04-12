package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rangiffler.data.PhotoEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class PhotoServiceJson {
    
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("countryId")
    private UUID countryId;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("description")
    private String description;

    @JsonProperty("username")
    private String username;

    public static PhotoServiceJson fromEntity(PhotoEntity entity) {
        byte[] photo = entity.getPhoto();
        PhotoServiceJson json = new PhotoServiceJson();
        json.setId(entity.getId());
        json.setDescription(entity.getDescription());
        json.setUsername(entity.getUsername());
        json.setCountryId(entity.getCountryId());
        json.setPhoto(photo != null && photo.length > 0 ? new String(entity.getPhoto(), StandardCharsets.UTF_8) : null);
        return json;
    }
}
