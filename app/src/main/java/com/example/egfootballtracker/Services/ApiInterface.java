package com.example.egfootballtracker.Services;



import com.example.egfootballtracker.Model.SportNewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<SportNewsList> getMatchNews(@Query("country") String country, @Query("category")
            String cat, @Query("apiKey") String name);

//    @GET("search")
//    Call<SportVideosResponse> getMatchVideos(@Query("key") String key, @Query("channelId")
//            String chId, @Query("part") String part);

}
