package com.rangiffler.controller;

import com.rangiffler.model.CountryJson;
import com.rangiffler.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Country Controller", description = "Using for working with countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Receive all countries",
            description = "Receive all countries",
            tags = {"Country Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Country received successfully")
    })
    public List<CountryJson> getAllCountries() {
        return countryService.getAllCountries();
    }

}
