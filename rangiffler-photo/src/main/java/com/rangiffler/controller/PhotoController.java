package com.rangiffler.controller;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import com.rangiffler.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public PhotoServiceJson editPhoto(@RequestBody PhotoJson photoJson) {
        return photoService.editPhoto(photoJson);
    }

    @DeleteMapping("/photos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePhoto(@PathVariable UUID id) {
        photoService.deletePhoto(id);
    }

}
