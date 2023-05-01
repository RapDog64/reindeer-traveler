package com.rangiffler.api.service;

import com.rangiffler.model.CountryJson;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.rangiffler.config.AppConfig.config;

public class CountryClient extends RestService {

    public CountryClient() {
        super(config.geoUrl());
    }

    private final CountryService countryClient = retrofit.create(CountryService.class);

    @Step("Send REST GET('/countries/{id}') request to geo service")
    public CountryJson findCountry(UUID id) throws IOException {
        return countryClient.findById(id)
                .execute()
                .body();
    }

    @Step("Send REST GET('/countries}') request to geo service")
    public List<CountryJson> getAllCountries() throws IOException {
        return countryClient.getAllCountries()
                .execute()
                .body();
    }
}
