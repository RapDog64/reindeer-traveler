package com.rangiffler.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@Table(name = "countries")
@EqualsAndHashCode
public class CountryEntity {

    @Id
    @Column(name = "country_id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "country_code", nullable = false)
    private String code;

    @Column(name = "country_name", nullable = false)
    private String name;
}
