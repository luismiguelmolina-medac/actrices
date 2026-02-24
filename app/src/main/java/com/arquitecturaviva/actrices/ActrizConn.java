package com.arquitecturaviva.actrices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ActrizConn {
    @GET("/")
    Call<List<Actriz>> getActrices();

    @GET("/{id}")
    Call<Actriz> getActri(@Path("id") int id);

    @POST("/")
    Call<Void> createActriz(@Body Actriz actriz);

    @PUT("/")
    Call<Void> updateActriz(@Body Actriz actriz);

    @DELETE("/")
    Call<Void> deleteActriz(@Query("id") Integer id);

}
