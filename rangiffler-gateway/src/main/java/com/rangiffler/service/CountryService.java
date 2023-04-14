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

    public final List<CountryJson> countries = List.of(
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("ru")
                    .name("Russia")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("it")
                    .name("Italy")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("de")
                    .name("Germany")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("fr")
                    .name("France")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("FJ")
                    .name("Fiji")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("TZ")
                    .name("Tanzania")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("EH")
                    .name("Western Sahara")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("CA")
                    .name("Canada")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("US")
                    .name("United States")
                    .build(),
            CountryJson.builder()
                    .id(UUID.randomUUID())
                    .code("KZ")
                    .name("Kazakhstan")
                    .build());

    public List<CountryJson> getAllCountries() {
        return countryService.getAllCountries();
    }

    public CountryJson findById(UUID countryId) {
        return countryService.findById(countryId);
    }

    public CountryJson getCountryByCode(String code) {
        return countries.stream().filter(c -> c.getCode().equals(code)).findFirst().orElseThrow();
    }
}
