package com.rangiffler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

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
}
