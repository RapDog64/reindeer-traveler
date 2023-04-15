package com.rangiffler.controller;

import java.util.List;
import java.util.UUID;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhotoController {

  private final PhotoService photoService;

  @Autowired
  public PhotoController(PhotoService photoService) {
    this.photoService = photoService;
  }

  @GetMapping("/photos")
  @ResponseStatus(HttpStatus.OK)
  public List<PhotoJson> getPhotosForUser(@AuthenticationPrincipal Jwt principal) {
    String usernameFromJWT = principal.getClaim("sub");
    return photoService.getAllUserPhotos(usernameFromJWT);
  }

  @GetMapping("/friends/photos")
  @ResponseStatus(HttpStatus.OK)
  public List<PhotoJson> getAllFriendsPhotos(@AuthenticationPrincipal Jwt principal) {
    String usernameFromJWT = principal.getClaim("sub");
    return photoService.getAllFriendsPhotos(usernameFromJWT);
  }

  @PostMapping("/photos")
  @ResponseStatus(HttpStatus.CREATED)
  public PhotoJson addPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
    String usernameFromJWT = principal.getClaim("sub");
    photoJson.setUsername(usernameFromJWT);
    return photoService.addPhoto(photoJson);
  }

  @PutMapping("/photos/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PhotoJson editPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
    String usernameFromJWT = principal.getClaim("sub");
    photoJson.setUsername(usernameFromJWT);
    return photoService.editPhoto(photoJson, photoJson.getId());
  }

  @DeleteMapping("/photos")
  @ResponseStatus(HttpStatus.OK)
  public void deletePhoto(@RequestParam UUID photoId) {
    photoService.deletePhoto(photoId);
  }

}
