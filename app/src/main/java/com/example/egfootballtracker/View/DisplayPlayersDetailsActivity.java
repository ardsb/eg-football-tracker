package com.example.egfootballtracker.View;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egfootballtracker.Adapter.NewsAdapter;
import com.example.egfootballtracker.Adapter.PlayersProfileAdpater;
import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.Model.SportNews;
import com.example.egfootballtracker.Model.SportNewsList;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.Services.ApiClientNews;
import com.example.egfootballtracker.Services.ApiClientPlayerDetails;
import com.example.egfootballtracker.Services.ApiInterface;
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

public class DisplayPlayersDetailsActivity extends Activity {
    String TAG = DisplayPlayersDetailsActivity.class.getSimpleName();
    DatabaseReference myRef;
    List<PlayerDetails> playerDetails;
    RecyclerView recyclerView;
    private final static String Cat = "players";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_players_profile);

        recyclerView = findViewById(R.id.reyclerviewPlayerDetails);
        playerDetails = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("player-details");

//        getPlayerDetails();
    }
//    private void getPlayerDetails() {
//        ApiInterface apiInterface = ApiClientPlayerDetails.getPlayerDetails().create(ApiInterface.class);
//
//        Call<List<PlayerDetailsNew>> call = apiInterface.getPlayerDetails(Cat);
//
//        ((retrofit2.Call) call).enqueue(new Callback<PlayerDetailsNew>() {
//            @Override
//            public void onResponse(retrofit2.Call<PlayerDetailsNew> call, Response<PlayerDetailsNew> response) {
//
//
//                if (response.isSuccessful() && response.body().getPlayerAge().size() > 0) {
//                    List<SportNews> News = response.body().getArticle();
//                    NewsAdapter adapter = new NewsAdapter(News, R.layout.news_layout,
//                            getApplicationContext());
//
//                    recyclerView.setAdapter(adapter);
//
//                } else {
//                    Toast.makeText(DisplayPlayersDetailsActivity.this, response.message(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SportNewsList> call, Throwable t) {
//                Log.e(TAG, String.format("onFailure: %s", t.getMessage()));
//            }
//
//        });
//    }
    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                playerDetails.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    PlayerDetails playerDetailsDisplay = postSnapshot.getValue(PlayerDetails.class);

                    playerDetails.add(playerDetailsDisplay);
                }

                if (playerDetails.size() > 0) {

                    PlayersProfileAdpater adapter = new PlayersProfileAdpater
                            (DisplayPlayersDetailsActivity.this, playerDetails);


                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                            (DisplayPlayersDetailsActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
