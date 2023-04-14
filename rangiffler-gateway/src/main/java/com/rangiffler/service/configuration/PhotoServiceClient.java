package com.rangiffler.service.configuration;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rangiffler-photo", url = "http://127.0.0.1:8086")
public interface PhotoServiceClient {

    @GetMapping("/photos")
    List<PhotoJson> getPhotos(@RequestParam String username);

    @GetMapping("/photos")
    List<PhotoServiceJson> getPhotosForUser(@RequestParam String username);

    @GetMapping("/friends/photos")
    List<PhotoJson> getAllFriendsPhotos(@RequestParam String username);

    @PostMapping("/photos")
    PhotoJson addPhoto(@RequestBody PhotoJson photoJson);

    @PutMapping("/photos/{id}")
    PhotoServiceJson editPhoto(@RequestBody PhotoJson photoJson, @PathVariable UUID id);

    @DeleteMapping("/photos/{id}")
    void deletePhoto(@PathVariable UUID id);

}
