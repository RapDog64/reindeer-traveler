package com.rangiffler.service;


import com.rangiffler.model.*;
import com.rangiffler.service.configuration.PhotoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoService {

    private final UserService userService;
    private final CountryService countryService;
    private final PhotoServiceClient photoService;

    @Autowired
    public PhotoService(UserService userService, CountryService countryService, PhotoServiceClient photoService) {
        this.userService = userService;
        this.countryService = countryService;
        this.photoService = photoService;
    }

    List<PhotoJson> mainUserPhotoList = new ArrayList<>();
    public PhotoJson addPhoto(PhotoJson photoJson) {
        photoService.addPhoto(photoJson);
        return photoJson;
    }

    public List<PhotoJson> getAllUserPhotos(String username) {
        List<PhotoJson> usersPhoto = new ArrayList<>();
        List<PhotoServiceJson> photos = photoService.getPhotosForUser(username);
        if (!photos.isEmpty()) {
            for (PhotoServiceJson photo : photos) {
                CountryJson country = countryService.findById(photo.getCountryId());
                usersPhoto.add(PhotoServiceJson.fromPhotoServiceJson(photo, country));
            }
        }
        return usersPhoto;
    }

    public PhotoJson editPhoto(PhotoJson photoJson, UUID id) {
        PhotoServiceJson editPhoto = photoService.editPhoto(photoJson, id);
        return PhotoServiceJson.fromPhotoServiceJson(editPhoto, photoJson.getCountryJson());
    }

    public List<PhotoJson> getAllFriendsPhotos(String username) {
        List<PhotoJson> friendsPhoto = new ArrayList<>();
        List<UserJson> friends = userService.receivePeopleAround(username)
                .stream()
                .filter(userJson -> userJson.getFriendStatus() == FriendStatus.FRIEND)
                .toList();

        for (UserJson userJson : friends) {
            friendsPhoto.addAll(getAllUserPhotos(userJson.getUsername()));
        }

        return friendsPhoto;
    }

    public void deletePhoto(UUID photoId) {
        photoService.deletePhoto(photoId);
    }
}
