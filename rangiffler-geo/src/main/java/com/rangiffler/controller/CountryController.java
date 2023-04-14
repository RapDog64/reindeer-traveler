package com.rangiffler.controller;


import com.rangiffler.model.CountryJson;
import com.rangiffler.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryJson> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{id}")
    public CountryJson findById(@RequestParam UUID id) {
        return countryService.findById(id);
    }
}
