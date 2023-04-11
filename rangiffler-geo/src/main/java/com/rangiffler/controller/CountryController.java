package com.rangiffler.controller;


import com.rangiffler.model.CountryJson;
import com.rangiffler.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryJson> getAllCountries() {
        return countryService.getAllCountries();
    }
}
