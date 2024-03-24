package com.rangiffler.data;

import com.google.protobuf.NullValue;
import com.rangiffler.grpc.NullableString;
import com.rangiffler.grpc.Photo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.nio.charset.StandardCharsets;
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


    public static Photo toGrpcPhoto(PhotoEntity entity) {
        byte[] photo = entity.getPhoto();
        return Photo.newBuilder()
                .setId(String.valueOf(entity.getId()))
                .setPhoto(photo != null && photo.length > 0 ? new String(entity.getPhoto(), StandardCharsets.UTF_8) : null)
                .setCountryId(String.valueOf(entity.getCountryId()))
                .setUsername(entity.getUsername())
                .setDescription(checkDescriptionValue(entity))
                .build();
    }

    public static PhotoEntity toPhotoEntity(Photo photo) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(UUID.fromString(photo.getId()));
        photoEntity.setUsername(photo.getUsername());
        photoEntity.setPhoto(photo.getPhoto().getBytes(StandardCharsets.UTF_8));
        photoEntity.setCountryId(UUID.fromString(photo.getCountryId()));
        return photoEntity;
    }

    private static NullableString checkDescriptionValue(PhotoEntity entity) {
        return entity.getDescription() == null ? NullableString.newBuilder().setNull(NullValue.NULL_VALUE).build()
                : NullableString.newBuilder().setDescription(entity.getDescription()).build();
    }
}
