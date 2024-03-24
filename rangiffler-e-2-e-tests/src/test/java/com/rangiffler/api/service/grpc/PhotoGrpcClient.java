package com.rangiffler.api.service.grpc;

import com.google.protobuf.Empty;
import com.rangiffler.grpc.DeletePhotoRequest;
import com.rangiffler.grpc.NullableId;
import com.rangiffler.grpc.Photo;
import com.rangiffler.grpc.PhotoRequest;
import com.rangiffler.grpc.PhotoServiceGrpc;
import com.rangiffler.grpc.UsernameRequest;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

public class PhotoGrpcClient extends BaseGrpcClient {

    public PhotoGrpcClient() {
        super("localhost", 9089);
    }

    private final PhotoServiceGrpc.PhotoServiceBlockingStub geoService = PhotoServiceGrpc.newBlockingStub(CHANNEL);


    @Step("Send GRPC an add photo request to the photo service")
    public Photo addPhoto(PhotoRequest photo) {
        return geoService.addPhoto(photo).getPhoto();
    }

    @Step("Send GRPC get the user's photo request to the photo service")
    public List<Photo> getPhotosForUser(String username) {
        return geoService.getPhotosForUser(UsernameRequest.newBuilder().setUsername(username).build())
                .getPhotoList();
    }

    @Step("Send GRPC an edit photo request to the photo service")
    public Photo editPhoto(Photo photo) {
        return geoService.editPhoto(
                PhotoRequest.newBuilder()
                        .setPhoto(photo)
                        .build()
                ).getPhoto();
    }

    @Step("Send GRPC a delete photo request to the photo service")
    public void deleteTravelPhoto(NullableId requestId) {
        geoService.deletePhoto(DeletePhotoRequest.newBuilder().setId(requestId.getId()).build());
    }
}
