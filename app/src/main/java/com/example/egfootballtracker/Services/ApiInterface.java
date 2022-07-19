package com.example.egfootballtracker.Services;



import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.Model.SportNewsList;
import com.example.egfootballtracker.Model.SportVideosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<SportNewsList> getMatchNews(@Query("country") String country, @Query("category")
            String cat, @Query("apiKey") String name);

    @GET("search")
    Call<SportVideosResponse> getMatchVideos(@Query("key") String key, @Query("channelId")
            String chId, @Query("part") String part);

    @GET("players")
    Call<List<PlayerDetailsNew>> getPlayerDetails();

    @POST("players")
    Call<PlayerDetailsNew>setPlayerDetails(@Body PlayerDetailsNew playerDetailsNew);

    @DELETE("players/{id}")
    Call<Void> deletePlayer(@Path("id") int id);

    @PUT("players/{id}")
    Call<PlayerDetailsNew> editPlayer(@Path("id") int id,@Body PlayerDetailsNew playerDetailsNew);

}
