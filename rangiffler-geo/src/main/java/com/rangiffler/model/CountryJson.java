package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rangiffler.data.CountryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryJson {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    public static CountryJson fromEntity(CountryEntity entity) {
        CountryJson countryJson = new CountryJson();
        countryJson.setId(entity.getId());
        countryJson.setCode(entity.getCode());
        countryJson.setName(entity.getName());

        return countryJson;
    }
}
