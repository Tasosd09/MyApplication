package com.example.tasos.myapplication.api;

import android.util.Log;

import com.example.tasos.myapplication.api.ApiCalls;
import com.example.tasos.myapplication.model.UrlResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceCalls {



    public static void getURL(){
//        RatesRequestBody requestBody = new RatesRequestBody(true);

        ApiCalls invocation  = getRetrofitEngine().create(ApiCalls.class);
        Call<UrlResponse> call = invocation.getURL();
        call.enqueue(new Callback<UrlResponse>() {
            @Override
            public void onResponse(Call<UrlResponse> call, Response<UrlResponse> response) {
                Log.d("KRANIS", response.body().getUrl());
            }

            @Override
            public void onFailure(Call<UrlResponse> call, Throwable t) {
                Log.d("KRANIS", "Error");

            }
        });
    }

    private static Retrofit getRetrofitEngine(){
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl("https://leapstone-ac4af.firebaseio.com/")
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory
                        .create(gson))
                .build();

    }
}