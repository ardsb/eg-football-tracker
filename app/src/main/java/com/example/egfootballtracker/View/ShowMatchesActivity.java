package com.example.egfootballtracker.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.egfootballtracker.Adapter.LiveScoreMatchesAdapter;
import com.example.egfootballtracker.Model.Matches;
import com.example.egfootballtracker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ShowMatchesActivity extends AppCompatActivity {
    DatabaseReference myRef;
    List<Matches> matches;
    private String id,HomeTeamName,HostTeamName;

    RecyclerView recyclerviewOngoingMatche;
    String TAG = ShowMatchesActivity.class.getSimpleName();
    Button btnEdit,btnUpdate;
    EditText teamHomeScore,teamAwayScore,matchTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Match Scores");//Database name

        recyclerviewOngoingMatche = findViewById(R.id.recycler1);
        matches = new ArrayList<>();
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(recyclerviewOngoingMatche);


    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                matches.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Matches match = postSnapshot.getValue(Matches.class);

                    matches.add(match);
                }

                if (matches.size() > 0) {

                    LiveScoreMatchesAdapter liveScoreMatchesAdapter = new LiveScoreMatchesAdapter
                            (ShowMatchesActivity.this, matches);

                    LiveScoreMatchesAdapter adapter = new LiveScoreMatchesAdapter
                            (ShowMatchesActivity.this, matches);


                    recyclerviewOngoingMatche.setAdapter(adapter);
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