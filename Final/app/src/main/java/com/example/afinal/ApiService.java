package com.example.afinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("coinlist")
    Call<ApiResponse> getCoinList(@Query("key") String key, @Query("pref") String pref, @Query("page") int page, @Query("order") String order);
}

