package com.rangiffler.service;

import com.rangiffler.data.repository.CountryRepository;
import com.rangiffler.model.CountryJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
