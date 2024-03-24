package com.rangiffler.service;

import com.google.protobuf.Empty;
import com.rangiffler.grpc.DeletePhotoRequest;
import com.rangiffler.grpc.GetPhotosForUserResponse;
import com.rangiffler.grpc.Photo;
import com.rangiffler.grpc.PhotoRequest;
import com.rangiffler.grpc.PhotoResponse;
import com.rangiffler.grpc.PhotoServiceGrpc;
import com.rangiffler.grpc.UsernameRequest;
import com.rangiffler.model.CountryJson;
import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GrpcPhotoClient {

    private static final Logger LOG = LoggerFactory.getLogger(GrpcPhotoClient.class);

    @Autowired
    private GrpcGeoClient grpcGeoClient;

    @GrpcClient("grpcPhotoClient")
    private PhotoServiceGrpc.PhotoServiceBlockingStub photoServiceBlockingStub;

    public List<PhotoJson> getAllUserPhotos(String usernameFromJWT) {
        try {
            List<PhotoJson> usersPhoto = new ArrayList<>();
            final GetPhotosForUserResponse response = photoServiceBlockingStub.getPhotosForUser(UsernameRequest
                    .newBuilder()
                    .setUsername(usernameFromJWT)
                    .build());

            if (!response.getPhotoList().isEmpty()) {
                for (Photo photo : response.getPhotoList()) {
                    CountryJson country = grpcGeoClient.findById(UUID.fromString(photo.getCountryId()));
                    usersPhoto.add(PhotoServiceJson.fromPhotoServiceJson(photo, country));
                }
            }
            return usersPhoto;
        } catch (StatusRuntimeException e) {
            LOG.error("### Error while calling the gRPC server: ", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The gRPC operation has been cancelled", e);
        }
    }

    public PhotoJson addPhoto(PhotoJson photoJson) {
        try {
            final PhotoResponse response = photoServiceBlockingStub.addPhoto(PhotoRequest.newBuilder()
                    .setPhoto(PhotoJson.toPhotoGrpc(photoJson))
                    .build());

            final CountryJson country = grpcGeoClient.findById(UUID.fromString(response.getPhoto().getCountryId()));

            return PhotoJson.toPhotoJson(response.getPhoto(), country);
        } catch (StatusRuntimeException e) {
            LOG.error("### Error while calling the gRPC server: ", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The gRPC operation has been cancelled", e);
        }
    }

    public PhotoJson editPhoto(PhotoJson photoJson) {
        try {
            final PhotoResponse response = photoServiceBlockingStub.editPhoto(PhotoRequest.newBuilder()
                    .setPhoto(PhotoJson.toPhotoGrpc(photoJson))
                    .build());
            final CountryJson country = grpcGeoClient.findById(UUID.fromString(response.getPhoto().getCountryId()));

            return PhotoJson.toPhotoJson(response.getPhoto(), country);
        } catch (StatusRuntimeException e) {
            LOG.error("### Error while calling the gRPC server: ", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The gRPC operation has been cancelled", e);
        }
    }

    public void deletePhoto(UUID photoId) {
        photoServiceBlockingStub.deletePhoto(DeletePhotoRequest.newBuilder().setId(String.valueOf(photoId)).build());
    }
}
