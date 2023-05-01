package com.rangiffler.api.service;

import com.rangiffler.model.PhotoJson;
import io.qameta.allure.Step;

import java.io.IOException;

import static com.rangiffler.config.AppConfig.config;

public class PhotoClient extends RestService {

    public PhotoClient() {
        super(config.photoUrl());
    }

    private final PhotoService photoService = retrofit.create(PhotoService.class);

    @Step("Send REST POST('/photos') request to photo service")
    public void addPhoto(PhotoJson photo) throws IOException {
        photoService.addPhoto(photo)
                .execute()
                .body();
    }

}
