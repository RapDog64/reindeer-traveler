package com.rangiffler.api.service;

import com.rangiffler.model.PhotoJson;
import com.rangiffler.model.PhotoServiceJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.UUID;

public interface PhotoService {

    @POST("/photos")
    Call<PhotoServiceJson> addPhoto(@Body PhotoJson photoJson);

    @GET("/photos")
    Call<List<PhotoServiceJson>> getPhotosForUser(@Query("username") String username);

    @PUT("/photos/{id}")
    Call<PhotoServiceJson> editTravelPhoto(@Path("id") UUID id, @Body PhotoJson photoJson);

    @PUT("/photos/{id}")
    Call<PhotoServiceJson> editTravelPhoto(@Path("id") UUID id, @Body String photoJson);


    @DELETE("/photos/{id}")
    Call<Void> deleteTravelPhoto(@Path("id") UUID id);
}
