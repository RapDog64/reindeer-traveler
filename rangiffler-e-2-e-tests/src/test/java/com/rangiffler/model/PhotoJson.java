package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
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

    public PhotoJson(UUID id, CountryJson countryJson, String photo, String description, String username) {
        this.id = id;
        this.countryJson = countryJson;
        this.photo = photo;
        this.description = description;
        this.username = username;
    }

    public PhotoJson() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoJson photoJson)) return false;
        return Objects.equals(id, photoJson.id) && Objects.equals(countryJson, photoJson.countryJson) && Objects.equals(photo, photoJson.photo) && Objects.equals(description, photoJson.description) && Objects.equals(username, photoJson.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryJson, photo, description, username);
    }
}
