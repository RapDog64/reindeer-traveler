package com.rangiffler.service.configuration;

import com.rangiffler.model.CountryJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "rangiffler-geo", url = "http://127.0.0.1:8088")
public interface CountryServiceClient {

    @GetMapping("/countries")
    List<CountryJson> getAllCountries();
}
