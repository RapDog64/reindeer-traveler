package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public static PhotoJson fromPhotoServiceJson(PhotoServiceJson json, CountryJson countryJson) {
        PhotoJson photoJson = new PhotoJson();
        photoJson.setId(json.getId());
        photoJson.setCountryJson(countryJson);
        photoJson.setDescription(json.getDescription());
        photoJson.setUsername(json.getUsername());
        photoJson.setPhoto(json.getPhoto());
        return photoJson;
    }
}
