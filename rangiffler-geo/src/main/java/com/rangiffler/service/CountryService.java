package com.rangiffler.service;

import com.rangiffler.data.CountryEntity;
import com.rangiffler.data.repository.CountryRepository;
import com.rangiffler.exception.CountryNotFoundException;
import com.rangiffler.exception.InvalidCountryIdException;
import com.rangiffler.model.CountryJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.rangiffler.exception.ErrorMessage.COUNTY_NOT_FOUND;
import static com.rangiffler.exception.ErrorMessage.INVALID_COUNTRY_ID;
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
        Optional.ofNullable(id)
                .orElseThrow(() -> new InvalidCountryIdException(INVALID_COUNTRY_ID));
        CountryEntity entity = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(String.format(COUNTY_NOT_FOUND, id)));
        return CountryJson.fromEntity(entity);
    }
}
