package com.rangiffler.service.configuration;

import com.rangiffler.model.PhotoJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rangiffler-photo", url = "http://127.0.0.1:8086")
public interface PhotoServiceClient {

    @GetMapping("/photos")
    List<PhotoJson> getPhotosForUser(@RequestParam String username);

    @GetMapping("/friends/photos")
    List<PhotoJson> getAllFriendsPhotos();

    @PostMapping("/photos")
    PhotoJson addPhoto(@RequestBody PhotoJson photoJson);

    @PutMapping("/photos/{id}")
    PhotoJson editPhoto(@RequestBody PhotoJson photoJson);

    @DeleteMapping("/photos")
    void deletePhoto(@RequestParam UUID photoId);
}
