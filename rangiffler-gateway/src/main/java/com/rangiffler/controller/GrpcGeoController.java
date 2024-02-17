package com.rangiffler.controller;

import com.rangiffler.model.CountryJson;
import com.rangiffler.service.GrpcGeoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GrpcGeoController {

    private final GrpcGeoClient grpcGeoClient;

    @Autowired
    public GrpcGeoController(GrpcGeoClient grpcGeoClient) {
        this.grpcGeoClient = grpcGeoClient;
    }

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<CountryJson> getAllCountries() {
        return grpcGeoClient.getCountries();
    }

}
