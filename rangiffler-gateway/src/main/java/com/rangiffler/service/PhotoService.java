package com.rangiffler.service;


import com.rangiffler.model.CountryJson;
import com.rangiffler.model.FriendStatus;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import com.rangiffler.service.configuration.PhotoServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final UserService userService;
    private final CountryService countryService;
    private final PhotoServiceClient photoService;

    public PhotoJson addPhoto(PhotoJson photoJson) {
        photoService.addPhoto(photoJson);
        return photoJson;
    }

    public List<PhotoJson> getAllUserPhotos(String username) {
        List<PhotoServiceJson> photos = photoService.getPhotosForUser(username);
        if (photos.isEmpty()) {
            return new ArrayList<>();
        }

        return photos.stream()
                .map(photo -> {
                    CountryJson country = countryService.findById(photo.getCountryId());
                    return PhotoServiceJson.fromPhotoServiceJson(photo, country);
                })
                .toList();
    }

    public PhotoJson editPhoto(PhotoJson photoJson, UUID id) {
        PhotoServiceJson editPhoto = photoService.editPhoto(photoJson, id);
        return PhotoServiceJson.fromPhotoServiceJson(editPhoto, photoJson.getCountryJson());
    }

    public List<PhotoJson> getAllFriendsPhotos(String username) {
        return userService.receivePeopleAround(username)
                .stream()
                .filter(userJson -> userJson.getFriendStatus() == FriendStatus.FRIEND)
                .map(userJson -> getAllUserPhotos(userJson.getUsername()))
                .findFirst()
                .orElseGet(ArrayList::new);
    }

    public void deletePhoto(UUID photoId) {
        photoService.deletePhoto(photoId);
    }
}
