package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rangiffler.data.PhotoEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
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

    public static PhotoJson fromEntity(PhotoEntity entity) {
        PhotoJson photoJson = new PhotoJson();
        photoJson.setId(entity.getId());
        photoJson.setPhoto(entity.getSrc());
        photoJson.setDescription(entity.getDescription());
        photoJson.setUsername(entity.getUsername());
//        photoJson.setCountryJson(entity.getCountryCode());
        return photoJson;
    }
}
