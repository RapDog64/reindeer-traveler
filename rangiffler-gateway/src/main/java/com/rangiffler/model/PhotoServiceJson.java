package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rangiffler.grpc.Photo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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


    public static PhotoJson fromPhotoServiceJson(Photo json, CountryJson countryJson) {
        PhotoJson photoJson = new PhotoJson();
        photoJson.setId(UUID.fromString(json.getId()));
        photoJson.setCountryJson(countryJson);
        photoJson.setDescription(json.getDescription().getDescription());
        photoJson.setUsername(json.getUsername());
        photoJson.setPhoto(json.getPhoto());
        return photoJson;
    }
}
