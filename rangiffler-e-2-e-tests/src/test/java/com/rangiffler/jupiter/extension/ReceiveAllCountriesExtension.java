package com.rangiffler.jupiter.extension;

import com.rangiffler.api.service.CountryClient;
import com.rangiffler.model.CountryJson;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class ReceiveAllCountriesExtension implements BeforeAllCallback {

    private final CountryClient countryService = new CountryClient();
    public static List<CountryJson> ALL_COUNTRIES = null;

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        ALL_COUNTRIES = countryService.getAllCountries();
    }
}
