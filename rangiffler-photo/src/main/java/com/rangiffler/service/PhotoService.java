package com.rangiffler.service;

import com.rangiffler.data.PhotoEntity;
import com.rangiffler.data.repository.PhotoRepository;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public List<PhotoServiceJson> getAllUserPhotos(String username) {
        return photoRepository.findAllByUsername(username)
                .stream()
                .map(PhotoServiceJson::fromEntity)
                .collect(toList());
    }

    public List<PhotoJson> getAllFriendsPhotos(String username) {
        photoRepository.findAllByUsername(username);
        return null;
    }

    public void addPhoto(PhotoJson photoJson) {
        PhotoEntity photoEntity = PhotoJson.toPhotoEntity(photoJson);
        photoRepository.save(photoEntity);
    }


    public void deletePhoto(UUID photoId) {
    }

    public PhotoJson editPhoto(PhotoJson photoJson) {
        return null;
    }
}
