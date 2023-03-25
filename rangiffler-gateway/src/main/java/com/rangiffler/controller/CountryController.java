package com.rangiffler.controller;

import java.util.List;

import com.rangiffler.model.CountryJson;
import com.rangiffler.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {


  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping("/countries")
  public List<CountryJson> getAllCountries() {
    return countryService.getAllCountries();
  }

}
