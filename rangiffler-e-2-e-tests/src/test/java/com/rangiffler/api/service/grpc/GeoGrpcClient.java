package com.rangiffler.api.service.grpc;

import com.google.protobuf.Empty;
import com.rangiffler.grpc.CountryRequest;
import com.rangiffler.grpc.CountryResponse;
import com.rangiffler.grpc.GeoServiceGrpc;
import com.rangiffler.model.CountryJson;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

public class GeoGrpcClient extends BaseGrpcClient {

    public GeoGrpcClient() {
        super("localhost", 9099);
    }

    private final GeoServiceGrpc.GeoServiceBlockingStub geoService = GeoServiceGrpc.newBlockingStub(CHANNEL);

    @Step("Send GRPC('/countries/{id}') request to geo service")
    public CountryJson getCountryBy(UUID id) {
        CountryResponse response = geoService.findCountryById(CountryRequest.newBuilder()
                .setId(String.valueOf(id))
                .build());

        return CountryJson.fromGrpcMessage(response.getCountry());
    }

    @Step("Send GRPC('/countries}') request to geo service")
    public List<CountryJson> getAllCountries() {
        return geoService.getAllCountries(Empty.getDefaultInstance())
                .getCountriesList()
                .stream()
                .map(CountryJson::fromGrpcMessage)
                .toList();
    }
}
