package com.rangiffler.data.dao.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class CountryEntity {

    private UUID id;
    private String code;
    private String name;
}
