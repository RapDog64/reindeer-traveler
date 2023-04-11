package com.rangiffler.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "src", nullable = false)
    private String src;


    @Column(name = "countryCode", nullable = false)
    private String countryCode;

    @Column(name = "description")
    private String description;
}
