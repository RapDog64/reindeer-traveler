package com.rangiffler.service;

import com.google.protobuf.Empty;
import com.rangiffler.grpc.CountryRequest;
import com.rangiffler.grpc.GeoServiceGrpc;
import com.rangiffler.model.CountryJson;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class GrpcGeoClient {

    private static final Logger LOG = LoggerFactory.getLogger(GrpcGeoClient.class);
    private static final Empty EMPTY = Empty.getDefaultInstance();

    @GrpcClient("grpcGeoClient")
    private GeoServiceGrpc.GeoServiceBlockingStub geoServiceBlockingStub;

    public List<CountryJson> getCountries() {
        try {
            return geoServiceBlockingStub.getAllCountries(EMPTY).getCountriesList()
                    .stream()
                    .map(CountryJson::fromGrpcMessage)
                    .toList();
        } catch (StatusRuntimeException e) {
            LOG.error("### Error while calling the gRPC server ", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The gRPC operation has been cancelled", e);
        }
    }

    public CountryJson findById(UUID countryId) {
        try {
            return CountryJson.fromGrpcMessage(geoServiceBlockingStub.findCountryById(CountryRequest.newBuilder()
                    .setId(String.valueOf(countryId))
                    .build())
                    .getCountry());
        } catch (StatusRuntimeException e) {
            LOG.error("### Error while calling the gRPC server ", e);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "The gRPC operation has been cancelled", e);
        }
    }
}
