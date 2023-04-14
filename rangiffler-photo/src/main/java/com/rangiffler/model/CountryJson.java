package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class CountryJson {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;
}
