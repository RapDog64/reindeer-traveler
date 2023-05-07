package com.rangiffler.api.service;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import io.qameta.allure.Step;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @Step("Send REST GET('/photos?username={0}') request to photo service")
    public List<PhotoServiceJson> getPhotosForUser(String username) throws IOException {
       return photoService.getPhotosForUser(username)
                .execute()
                .body();
    }

    @Step("Send REST PUT('/photos/{id}') request to photo service")
    public Response<PhotoServiceJson> editPhoto(UUID id, PhotoJson photo) throws IOException {
       return photoService.editPhoto(id, photo)
                .execute();
    }


    @Step("Send REST PUT('/photos/{id}') request to photo service")
    public Response<PhotoServiceJson> editPhoto(UUID id, String body) throws IOException {
       return photoService.editPhoto(id, body)
                .execute();
    }
}
