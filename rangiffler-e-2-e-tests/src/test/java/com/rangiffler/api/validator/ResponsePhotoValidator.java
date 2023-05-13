package com.rangiffler.api.validator;

import com.rangiffler.model.PhotoJson;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponsePhotoValidator {

    public static void validatePhoto(PhotoJson expectedPhoto, PhotoJson actualPhoto) {
        assertAll(
                () -> assertEquals(expectedPhoto.getDescription(), actualPhoto.getDescription()),
                () -> assertEquals(expectedPhoto.getUsername(), actualPhoto.getUsername()),
                () -> assertEquals(expectedPhoto.getPhoto(), actualPhoto.getPhoto()),
                () -> assertEquals(expectedPhoto.getCountryJson().getId(), actualPhoto.getCountryJson().getId())
        );
    }
}
