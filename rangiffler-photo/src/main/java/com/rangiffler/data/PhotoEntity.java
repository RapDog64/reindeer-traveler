package com.rangiffler.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Table(name = "photos")
@Entity
@EqualsAndHashCode
public class PhotoEntity {

    @Id
    @Column(name = "photo_id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "photo", nullable = false,  columnDefinition = "bytea")
    private byte[] photo;

    @Column(name = "country_id", nullable = false)
    private UUID countryId;

    @Column(name = "description")
    private String description;
}
