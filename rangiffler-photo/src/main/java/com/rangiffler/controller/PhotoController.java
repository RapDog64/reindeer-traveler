package com.rangiffler.controller;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import com.rangiffler.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/photos")
    @ResponseStatus(HttpStatus.OK)
    public List<PhotoServiceJson> getPhotosForUser(@RequestParam String username) {
        return photoService.getAllUserPhotos(username);
    }

    @GetMapping("/friends/photos")
    public List<PhotoJson> getAllFriendsPhotos(@RequestParam String username) {
        return photoService.getAllFriendsPhotos(username);
    }

    @PostMapping("/photos")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhoto(@RequestBody PhotoJson photoJson) {
        photoService.addPhoto(photoJson);
    }

    @PutMapping("/photos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PhotoServiceJson editPhoto(@PathVariable UUID id, @RequestBody PhotoJson photoJson) {
        return photoService.editPhoto(photoJson, id);
    }

    @DeleteMapping("/photos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePhoto(@PathVariable UUID id) {
        photoService.deletePhoto(id);
    }

}
