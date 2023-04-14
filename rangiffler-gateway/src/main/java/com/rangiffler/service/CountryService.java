package com.rangiffler.service;

import com.rangiffler.model.CountryJson;
import com.rangiffler.service.configuration.CountryServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryServiceClient countryService;

    public List<CountryJson> getAllCountries() {
        return countryService.getAllCountries();
    }

    public CountryJson findById(UUID countryId) {
        return countryService.findById(countryId);
    }
}
