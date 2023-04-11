package com.rangiffler.service;

import com.rangiffler.data.repository.PhotoRepository;
import com.rangiffler.model.PhotoJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public List<PhotoJson> getAllUserPhotos(String username) {
        return photoRepository.findAllByUsername(username)
                .stream()
                .map(PhotoJson::fromEntity)
                .collect(toList());
    }

    public List<PhotoJson> getAllFriendsPhotos(String username) {
        photoRepository.findAllByUsername(username);
        return null;
    }

    public PhotoJson addPhoto(PhotoJson photoJson) {
        return null;
    }


    public void deletePhoto(UUID photoId) {
    }

    public PhotoJson editPhoto(PhotoJson photoJson) {
        return null;
    }
}
