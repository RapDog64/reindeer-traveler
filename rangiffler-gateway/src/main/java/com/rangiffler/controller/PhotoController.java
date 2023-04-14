package com.rangiffler.controller;

import java.util.List;
import java.util.UUID;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {

  private final PhotoService photoService;

  @Autowired
  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  @GetMapping("/photos")
  public List<PhotoJson> getPhotosForUser(@AuthenticationPrincipal Jwt principal) {
    String usernameFromJWT = principal.getClaim("sub");
    return photoService.getAllUserPhotos(usernameFromJWT);
  }

  @GetMapping("/friends/photos")
  public List<PhotoJson> getAllFriendsPhotos(@AuthenticationPrincipal Jwt principal) {
    String usernameFromJWT = principal.getClaim("sub");
    return photoService.getAllFriendsPhotos(usernameFromJWT);
  }

  @PostMapping("/photos")
  public PhotoJson addPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
    String usernameFromJWT = principal.getClaim("sub");
    photoJson.setUsername(usernameFromJWT);
    return photoService.addPhoto(photoJson);
  }

  @PatchMapping("/photos/{id}")
  public PhotoJson editPhoto(@RequestBody PhotoJson photoJson) {
    return photoService.editPhoto(photoJson);
  }

  @DeleteMapping("/photos")
  public void deletePhoto(@RequestParam UUID photoId) {
    photoService.deletePhoto(photoId);
  }

}
