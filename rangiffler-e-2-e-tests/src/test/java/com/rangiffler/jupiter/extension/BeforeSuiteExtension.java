package com.rangiffler.jupiter.extension;

import com.rangiffler.api.service.grpc.GeoGrpcClient;
import com.rangiffler.model.CountryJson;

import java.util.List;

public class BeforeSuiteExtension implements SuiteCallbacks {

    private final GeoGrpcClient countryService = new GeoGrpcClient();
    public static List<CountryJson> ALL_COUNTRIES = null;

    @Override
    public void beforeSuiteCallback() {
        // TODO: Request ALL_COUNTRIES from the database
        ALL_COUNTRIES = countryService.getAllCountries();
    }
}
