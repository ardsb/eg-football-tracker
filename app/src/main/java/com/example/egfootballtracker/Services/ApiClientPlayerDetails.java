package com.example.egfootballtracker.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientPlayerDetails {

    public static final String BASE_URL = "http://localhost:8080/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getPlayerDetails() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
