package com.rangiffler.service;

import com.rangiffler.data.PhotoEntity;
import com.rangiffler.data.repository.PhotoRepository;
import com.rangiffler.exception.InvalidRequestBodyException;
import com.rangiffler.exception.InvalidUsernameException;
import com.rangiffler.exception.PhotoNotFoundException;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.rangiffler.exception.ErrorMessages.*;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public List<PhotoServiceJson> getAllUserPhotos(String username) {
        Optional.ofNullable(username).orElseThrow(() -> new InvalidUsernameException(INVALID_USERNAME));
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
        Optional.ofNullable(photoJson).orElseThrow(() -> new InvalidRequestBodyException(INVALID_REQUEST_BODY));
        PhotoEntity photoEntity = PhotoJson.toPhotoEntity(photoJson);
        photoRepository.save(photoEntity);
    }

    public void deletePhoto(UUID photoId) {
        Optional.ofNullable(photoId).orElseThrow(() -> new InvalidRequestBodyException(INVALID_REQUEST_BODY));
        photoRepository.findById(photoId).orElseThrow(() -> new PhotoNotFoundException(PHOTO_NOT_FOUND));
        photoRepository.deleteById(photoId);
    }

    public PhotoServiceJson editPhoto(PhotoJson photoJson) {
        Optional.ofNullable(photoJson)
                .orElseThrow(() -> new InvalidRequestBodyException(INVALID_REQUEST_BODY));
        PhotoEntity photoEntity = photoRepository.findById(photoJson.getId())
                .orElseThrow(() -> new PhotoNotFoundException(PHOTO_NOT_FOUND));

        photoEntity.setDescription(photoJson.getDescription());
        photoEntity.setCountryId(photoJson.getCountryJson().getId());
        PhotoEntity savedEntity = photoRepository.save(photoEntity);
        return PhotoServiceJson.fromEntity(savedEntity);
    }
}
