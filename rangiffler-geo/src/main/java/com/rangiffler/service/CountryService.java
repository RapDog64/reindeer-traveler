package com.rangiffler.service;

import com.rangiffler.data.CountryEntity;
import com.rangiffler.data.repository.CountryRepository;
import com.rangiffler.model.CountryJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryJson> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryJson::fromEntity)
                .collect(toList());
    }

    public CountryJson findById(UUID id) {
        Optional<CountryEntity> country = countryRepository.findById(id);
        if (country.isEmpty()) {
            throw new RuntimeException("No country is found");
        }

        return CountryJson.fromEntity(country.get());
    }
}
