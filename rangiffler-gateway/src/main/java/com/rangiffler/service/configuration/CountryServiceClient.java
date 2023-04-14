package com.rangiffler.service.configuration;

import com.rangiffler.model.CountryJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "rangiffler-geo", url = "http://127.0.0.1:8088")
public interface CountryServiceClient {

    @GetMapping("/countries")
    List<CountryJson> getAllCountries();

    @GetMapping("/countries/{id}")
    CountryJson findById(@RequestParam UUID id);
}
