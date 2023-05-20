package com.rangiffler.service;

import com.rangiffler.data.PhotoEntity;
import com.rangiffler.data.repository.PhotoRepository;
import com.rangiffler.exception.InvalidPhotoIdFormatException;
import com.rangiffler.exception.InvalidRequestBodyException;
import com.rangiffler.exception.InvalidUsernameException;
import com.rangiffler.exception.PhotoIdsException;
import com.rangiffler.exception.PhotoNotFoundException;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.rangiffler.exception.ErrorMessages.DIFFERENT_IDS_PROVIDED;
import static com.rangiffler.exception.ErrorMessages.INVALID_PHOTO_ID_FORMAT;
import static com.rangiffler.exception.ErrorMessages.INVALID_REQUEST_BODY;
import static com.rangiffler.exception.ErrorMessages.INVALID_USERNAME;
import static com.rangiffler.exception.ErrorMessages.PHOTO_NOT_FOUND;
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

    public PhotoServiceJson addPhoto(PhotoJson photoJson) {
        Optional.ofNullable(photoJson)
                .orElseThrow(() -> new InvalidRequestBodyException(INVALID_REQUEST_BODY));
        PhotoEntity photoEntity = PhotoJson.toPhotoEntity(photoJson);
        PhotoEntity entity = photoRepository.save(photoEntity);
        return PhotoServiceJson.fromEntity(entity);
    }

    public void deletePhoto(UUID photoId) {
        Optional.ofNullable(photoId)
                .orElseThrow(() -> new InvalidPhotoIdFormatException(INVALID_PHOTO_ID_FORMAT));
        photoRepository.findById(photoId)
                .orElseThrow(() -> new PhotoNotFoundException(String.format(PHOTO_NOT_FOUND, photoId)));
        photoRepository.deleteById(photoId);
    }

    public PhotoServiceJson editPhoto(PhotoJson photoJson, UUID id) {
        if (Optional.ofNullable(photoJson).isEmpty()) {
            throw new InvalidRequestBodyException(INVALID_REQUEST_BODY);
        }
        if (!Objects.equals(photoJson.getId(), id)) {
            throw new PhotoIdsException(String.format(DIFFERENT_IDS_PROVIDED, id, photoJson.getId()));
        }

        PhotoEntity photoEntity = photoRepository.findById(photoJson.getId())
                .orElseThrow(() -> new PhotoNotFoundException(String.format(PHOTO_NOT_FOUND, photoJson.getId())));

        photoEntity.setDescription(photoJson.getDescription());
        photoEntity.setCountryId(photoJson.getCountryJson().getId());
        PhotoEntity savedEntity = photoRepository.save(photoEntity);
        return PhotoServiceJson.fromEntity(savedEntity);
    }
}
