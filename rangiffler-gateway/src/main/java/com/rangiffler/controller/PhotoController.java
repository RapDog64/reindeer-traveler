package com.rangiffler.controller;

import java.util.List;
import java.util.UUID;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Photo Controller", description = "Using for working with the user's photo ")
public class PhotoController {

  private final PhotoService photoService;

  @GetMapping("/photos")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
          summary = "Receive the user's photos.",
          description = "Receive all the user's photos,",
          tags = {"Photo Controller"})
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Photos received successfully"),
          @ApiResponse(
                  responseCode = "400",
                  description = "Validation error")
  })
  public List<PhotoJson> getPhotosForUser(@AuthenticationPrincipal Jwt principal) {
    String usernameFromJWT = principal.getClaim("sub");
    return photoService.getAllUserPhotos(usernameFromJWT);
  }

  @GetMapping("/friends/photos")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
          summary = "Receive the user's friends' photos.",
          description = "Receive all the user's friends' photos",
          tags = {"Photo Controller"})
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Friends' photos received successfully")
  })
  public List<PhotoJson> getAllFriendsPhotos(@AuthenticationPrincipal Jwt principal) {
    String usernameFromJWT = principal.getClaim("sub");
    return photoService.getAllFriendsPhotos(usernameFromJWT);
  }

  @PostMapping("/photos")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(
          summary = "Add a photo",
          description = "Add a single photo",
          tags = {"Photo Controller"})
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Photo added successfully")
  })
  public PhotoJson addPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
    String usernameFromJWT = principal.getClaim("sub");
    photoJson.setUsername(usernameFromJWT);
    return photoService.addPhoto(photoJson);
  }

  @PutMapping("/photos/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
          summary = "Edit the user's photo",
          description = "Edit a single the user's photo",
          tags = {"Photo Controller"})
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Photo updated successfully")
  })
  public PhotoJson editPhoto(@AuthenticationPrincipal Jwt principal, @RequestBody PhotoJson photoJson) {
    String usernameFromJWT = principal.getClaim("sub");
    photoJson.setUsername(usernameFromJWT);
    return photoService.editPhoto(photoJson, photoJson.getId());
  }

  @DeleteMapping("/photos")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
          summary = "Remove the user's photo",
          description = "Remove a single the user's photo",
          tags = {"Photo Controller"})
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Photo removed successfully")
  })
  public void deletePhoto(@RequestParam UUID photoId) {
    photoService.deletePhoto(photoId);
  }

}
