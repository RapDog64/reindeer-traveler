package com.rangiffler.jupiter.extension;

import com.rangiffler.data.dao.entities.CountryEntity;
import com.rangiffler.data.dao.repositories.CountryRepository;
import com.rangiffler.model.CountryJson;

import java.util.List;
import java.util.stream.Collectors;

public class BeforeSuiteExtension implements SuiteCallbacks {

    public static List<CountryJson> ALL_COUNTRIES = null;
    private final CountryRepository countryRepository = new CountryRepository();

    @Override
    public void beforeSuiteCallback() {
        List<CountryEntity> countryEntities = countryRepository.getAllCountries()
                .orElseThrow(() -> new RuntimeException("Countries list doesn't exist"));
        ALL_COUNTRIES = countryEntities.stream()
                .map(CountryJson::fromEntity)
                .toList();
    }
}
