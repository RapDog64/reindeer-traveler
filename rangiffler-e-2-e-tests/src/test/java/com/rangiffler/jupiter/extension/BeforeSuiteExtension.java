package com.rangiffler.jupiter.extension;

import com.rangiffler.api.service.CountryClient;
import com.rangiffler.model.CountryJson;

import java.util.List;

public class BeforeSuiteExtension implements SuiteCallbacks {

    private final CountryClient countryService = new CountryClient();
    public static List<CountryJson> ALL_COUNTRIES = null;

    @Override
    public void beforeSuiteCallback() {
        ALL_COUNTRIES = countryService.getAllCountries();
    }
}
