package com.rangiffler.service.configuration;

import com.rangiffler.model.CountryJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rangiffler-geo", url = "${rangiffler-geo.base-uri}")
public interface CountryServiceClient {

    @GetMapping("/countries")
    List<CountryJson> getAllCountries();

    @GetMapping("/countries/{id}")
    CountryJson findById(@PathVariable UUID id);
}
