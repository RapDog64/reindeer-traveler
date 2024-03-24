package com.rangiffler.service;

import com.google.protobuf.Empty;
import com.rangiffler.data.PhotoEntity;
import com.rangiffler.data.repository.PhotoRepository;
import com.rangiffler.exception.InvalidRequestBodyException;
import com.rangiffler.exception.InvalidUsernameException;
import com.rangiffler.grpc.DeletePhotoRequest;
import com.rangiffler.grpc.GetPhotosForUserResponse;
import com.rangiffler.grpc.Photo;
import com.rangiffler.grpc.PhotoRequest;
import com.rangiffler.grpc.PhotoResponse;
import com.rangiffler.grpc.PhotoServiceGrpc;
import com.rangiffler.grpc.UsernameRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.rangiffler.exception.ErrorMessages.INVALID_REQUEST_BODY;
import static com.rangiffler.exception.ErrorMessages.INVALID_USERNAME;
import static com.rangiffler.exception.ErrorMessages.PHOTO_NOT_FOUND;
import static io.grpc.Status.NOT_FOUND;

@GrpcService
public class GrpcPhotoService extends PhotoServiceGrpc.PhotoServiceImplBase {

    private final PhotoRepository photoRepository;

    @Autowired
    public GrpcPhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void getPhotosForUser(final UsernameRequest request,
                                 final StreamObserver<GetPhotosForUserResponse> responseObserver) {
        final String username = Optional.of(request.getUsername())
                .orElseThrow(() -> new InvalidUsernameException(INVALID_USERNAME));

        final List<Photo> photoList = this.photoRepository.findAllByUsername(username)
                .stream()
                .map(PhotoEntity::toGrpcPhoto)
                .toList();

        responseObserver.onNext(GetPhotosForUserResponse.newBuilder().addAllPhoto(photoList)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void addPhoto(final PhotoRequest request, final StreamObserver<PhotoResponse> responseObserver) {
        final Photo photo = Optional.of(request.getPhoto())
                .orElseThrow(() -> new InvalidRequestBodyException(INVALID_REQUEST_BODY));

        PhotoEntity entity = photoRepository.save(PhotoEntity.toPhotoEntity(photo));
        responseObserver.onNext(PhotoResponse.newBuilder()
                .setPhoto(PhotoEntity.toGrpcPhoto(entity))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void editPhoto(final PhotoRequest request, final StreamObserver<PhotoResponse> responseObserver) {
        final Photo grpcPhoto = request.getPhoto();
        Optional<PhotoEntity> photoEntity = photoRepository.findById(UUID.fromString(grpcPhoto.getId()));

        if (photoEntity.isPresent()) {
            photoEntity.get().setDescription(grpcPhoto.getDescription().getDescription());
            PhotoEntity savedEntity = photoRepository.save(photoEntity.get());

            final PhotoResponse response = PhotoResponse.newBuilder()
                    .setPhoto(PhotoEntity.toGrpcPhoto(savedEntity))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(NOT_FOUND.withDescription(String.format(PHOTO_NOT_FOUND, grpcPhoto.getId()))
                    .asRuntimeException());
        }
    }

    @Override
    public void deletePhoto(final DeletePhotoRequest request, final StreamObserver<Empty> responseObserver) {
        final String photoId = request.getId();
        Optional<PhotoEntity> entity = photoRepository.findById(UUID.fromString(photoId));

        if (entity.isPresent()) {
            photoRepository.deleteById(UUID.fromString(photoId));
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(NOT_FOUND.withDescription(String.format(PHOTO_NOT_FOUND, photoId)).asRuntimeException());
        }
    }
}
