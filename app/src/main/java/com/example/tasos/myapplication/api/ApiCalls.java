package com.example.tasos.myapplication.api;

import com.estimote.coresdk.repackaged.retrofit_v1_9_0.retrofit.http.POST;
import com.example.tasos.myapplication.model.UrlResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tasos on 16-Sep-17.
 */

public interface ApiCalls {
    @GET("config.json")
    Call<UrlResponse> getURL();
}
