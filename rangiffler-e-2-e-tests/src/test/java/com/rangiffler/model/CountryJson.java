package com.rangiffler.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.rangiffler.grpc.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryJson {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    public static CountryJson fromGrpcMessage(Country grpcCountry) {
        return CountryJson.builder()
                .id(UUID.fromString(grpcCountry.getId()))
                .name(grpcCountry.getName())
                .code(grpcCountry.getCode())
                .build();
    }

}
