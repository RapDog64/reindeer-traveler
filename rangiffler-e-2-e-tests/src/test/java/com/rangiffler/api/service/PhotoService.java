package com.rangiffler.api.service;

import com.rangiffler.model.PhotoJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PhotoService {

    @POST("/photos")
    Call<Void> addPhoto(@Body PhotoJson photoJson);
}
