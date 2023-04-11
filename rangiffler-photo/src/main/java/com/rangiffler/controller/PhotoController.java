package com.rangiffler.controller;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/photos")
    public List<PhotoJson> getPhotosForUser(@RequestParam String username) {
        return photoService.getAllUserPhotos(username);
    }

    @GetMapping("/friends/photos")
    public List<PhotoJson> getAllFriendsPhotos(@RequestParam String username) {
        return photoService.getAllFriendsPhotos(username);
    }

    @PostMapping("/photos")
    public PhotoJson addPhoto(@RequestBody PhotoJson photoJson) {
        return photoService.addPhoto(photoJson);
    }

    @PutMapping("/photos/{id}")
    public PhotoJson editPhoto(@RequestBody PhotoJson photoJson) {
        return photoService.editPhoto(photoJson);
    }

    @DeleteMapping("/photos")
    public void deletePhoto(@RequestParam UUID photoId) {
        photoService.deletePhoto(photoId);
    }

}
