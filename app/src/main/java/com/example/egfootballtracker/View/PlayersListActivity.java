package com.example.egfootballtracker.View;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egfootballtracker.Adapter.PlayersProfileAdpater;
import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.Services.ApiInterface;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayersListActivity extends Activity {
    String TAG = PlayersListActivity.class.getSimpleName();
    DatabaseReference myRef;
    List<PlayerDetails> playerDetails;
    RecyclerView recyclerViewTest;
    ApiInterface apiInterface;
Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_players_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewTest=findViewById(R.id.reyclerviewPlayerDetails);
        recyclerViewTest.setHasFixedSize(true);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<PlayerDetails>> call = apiInterface.getPlayerDetails();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<PlayerDetails>> call, Response<List<PlayerDetails>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(PlayersListActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PlayerDetails> playerDetailsList = response.body();
                PlayersProfileAdpater playersProfileAdpater = new PlayersProfileAdpater(PlayersListActivity.this, playerDetailsList);
                recyclerViewTest.setAdapter(playersProfileAdpater);

            }

            @Override
            public void onFailure(Call<List<PlayerDetails>> call, Throwable t) {
                Toast.makeText(PlayersListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
