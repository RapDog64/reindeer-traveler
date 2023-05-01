package com.rangiffler.api.service;

import com.rangiffler.model.CountryJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    @GET("/countries/{id}")
    Call<CountryJson> findById(@Path("id") UUID id);

    @GET("/countries")
    Call<List<CountryJson>> getAllCountries();


}
