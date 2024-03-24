package com.rangiffler.controller;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.service.GrpcPhotoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GrpcPhotoController {

    private final GrpcPhotoClient photoClient;

    @Autowired
    public GrpcPhotoController(GrpcPhotoClient photoClient) {
        this.photoClient = photoClient;
    }

    @GetMapping("/photos")
    @ResponseStatus(HttpStatus.OK)
    public List<PhotoJson> getPhotosForUser(@AuthenticationPrincipal Jwt principal) {
        String usernameFromJWT = principal.getClaim("sub");
        return photoClient.getAllUserPhotos(usernameFromJWT);
    }

    @PostMapping("/photos")
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoJson addPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
        String usernameFromJWT = principal.getClaim("sub");
        photoJson.setUsername(usernameFromJWT);
        return photoClient.addPhoto(photoJson);
    }

    @PutMapping("/photos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PhotoJson editPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
        String usernameFromJWT = principal.getClaim("sub");
        photoJson.setUsername(usernameFromJWT);
        return photoClient.editPhoto(photoJson);
    }

    @GetMapping("/friends/photos")
    @ResponseStatus(HttpStatus.OK)
    public List<PhotoJson> getAllFriendsPhotos(@AuthenticationPrincipal Jwt principal) {
        String usernameFromJWT = principal.getClaim("sub");
        return photoClient.getAllUserPhotos(usernameFromJWT);
    }

    @DeleteMapping("/photos")
    @ResponseStatus(HttpStatus.OK)
    public void deletePhoto(@RequestParam UUID photoId) {
        photoClient.deletePhoto(photoId);
    }
}