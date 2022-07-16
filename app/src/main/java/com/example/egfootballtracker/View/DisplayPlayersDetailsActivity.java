package com.example.egfootballtracker.View;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egfootballtracker.Adapter.NewsAdapter;
import com.example.egfootballtracker.Adapter.PlayersProfileAdpater;
import com.example.egfootballtracker.Adapter.TestAdapter;
import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.Model.SportNews;
import com.example.egfootballtracker.Model.SportNewsList;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.Services.ApiClientNews;
import com.example.egfootballtracker.Services.ApiClientPlayerDetails;
import com.example.egfootballtracker.Services.ApiInterface;
import com.example.egfootballtracker.View.DisplayPlayersDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayPlayersDetailsActivity extends Activity {
    String TAG = DisplayPlayersDetailsActivity.class.getSimpleName();
    DatabaseReference myRef;
    List<PlayerDetails> playerDetails;
    RecyclerView recyclerViewTest;
    ApiInterface apiInterface;
Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_players_profile);

        recyclerViewTest=findViewById(R.id.reyclerviewPlayerDetails);
        recyclerViewTest.setHasFixedSize(true);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));
        btnDelete=findViewById(R.id.btnDeletePlayerList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);


        btnDelete=findViewById(R.id.btnDeletePlayerList);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlayer();
            }
        });

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<PlayerDetailsNew>> call = apiInterface.getPlayerDetails();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<PlayerDetailsNew>> call, Response<List<PlayerDetailsNew>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DisplayPlayersDetailsActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PlayerDetailsNew> playerDetailsNewList = response.body();
                TestAdapter testAdapter = new TestAdapter(DisplayPlayersDetailsActivity.this, playerDetailsNewList);
                recyclerViewTest.setAdapter(testAdapter);

            }

            @Override
            public void onFailure(Call<List<PlayerDetailsNew>> call, Throwable t) {
                Toast.makeText(DisplayPlayersDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
    private void deletePlayer() {

        Call<Void> call = apiInterface.deletePlayer(3);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Toast.makeText(DisplayPlayersDetailsActivity.this, "Deleted Successfully : " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
//        recyclerView = findViewById(R.id.reyclerviewPlayerDetails);
//        playerDetails = new ArrayList<>();
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("player-details");
//
////        getPlayerDetails();
//    }
////    private void getPlayerDetails() {
////        ApiInterface apiInterface = ApiClientPlayerDetails.getPlayerDetails().create(ApiInterface.class);
////
////        Call<List<PlayerDetailsNew>> call = apiInterface.getPlayerDetails(Cat);
////
////        ((retrofit2.Call) call).enqueue(new Callback<PlayerDetailsNew>() {
////            @Override
////            public void onResponse(retrofit2.Call<PlayerDetailsNew> call, Response<PlayerDetailsNew> response) {
////
////
////                if (response.isSuccessful() && response.body().getPlayerAge().size() > 0) {
////                    List<SportNews> News = response.body().getArticle();
////                    NewsAdapter adapter = new NewsAdapter(News, R.layout.news_layout,
////                            getApplicationContext());
////
////                    recyclerView.setAdapter(adapter);
////
////                } else {
////                    Toast.makeText(DisplayPlayersDetailsActivity.this, response.message(),
////                            Toast.LENGTH_SHORT).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<SportNewsList> call, Throwable t) {
////                Log.e(TAG, String.format("onFailure: %s", t.getMessage()));
////            }
////
////        });
////    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                playerDetails.clear();
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//
//                    PlayerDetails playerDetailsDisplay = postSnapshot.getValue(PlayerDetails.class);
//
//                    playerDetails.add(playerDetailsDisplay);
//                }
//
//                if (playerDetails.size() > 0) {
//
//                    PlayersProfileAdpater adapter = new PlayersProfileAdpater
//                            (DisplayPlayersDetailsActivity.this, playerDetails);
//
//
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
//                            (DisplayPlayersDetailsActivity.this);
//                    recyclerView.setLayoutManager(mLayoutManager);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });



}
