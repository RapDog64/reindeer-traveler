package com.rangiffler.service;

import com.google.protobuf.Empty;
import com.rangiffler.data.CountryEntity;
import com.rangiffler.data.repository.CountryRepository;
import com.rangiffler.grpc.CountriesResponse;
import com.rangiffler.grpc.Country;
import com.rangiffler.grpc.CountryRequest;
import com.rangiffler.grpc.CountryResponse;
import com.rangiffler.grpc.GeoServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.rangiffler.exception.ErrorMessage.COUNTRIES_NOT_FOUND;
import static com.rangiffler.exception.ErrorMessage.COUNTY_NOT_FOUND;
import static io.grpc.Status.NOT_FOUND;

@GrpcService
public class GrpcCountryService extends GeoServiceGrpc.GeoServiceImplBase {

    private final CountryRepository countryRepository;

    public GrpcCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void getAllCountries(Empty request, StreamObserver<CountriesResponse> responseObserver) {
        List<CountryEntity> entities = countryRepository.findAll();

        if (entities.isEmpty()) {
            responseObserver.onError(NOT_FOUND.withDescription(COUNTRIES_NOT_FOUND).asRuntimeException());
        }

        final CountriesResponse response = CountriesResponse.newBuilder()
                .addAllCountries(entities.stream()
                        .map(countryEntity -> Country.newBuilder()
                                .setId(String.valueOf(countryEntity.getId()))
                                .setCode(countryEntity.getCode())
                                .setName(countryEntity.getName())
                                .build())
                        .toList())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findCountryById(CountryRequest request, StreamObserver<CountryResponse> responseObserver) {
        final String id = request.getId();

        Optional<CountryEntity> entity = countryRepository.findById(UUID.fromString(id));

        if (entity.isEmpty()) {
            responseObserver.onError(NOT_FOUND.withDescription(String.format(COUNTY_NOT_FOUND, id)).asRuntimeException());
        } else {
            final CountryResponse response = CountryResponse.newBuilder()
                    .setCountry(Country.newBuilder()
                            .setId(String.valueOf(entity.get().getId()))
                            .setCode(entity.get().getCode())
                            .setName(entity.get().getName()))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
