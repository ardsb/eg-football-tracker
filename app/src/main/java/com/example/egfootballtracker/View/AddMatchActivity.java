package com.example.egfootballtracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egfootballtracker.Model.Matches;
import com.example.egfootballtracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMatchActivity extends AppCompatActivity {

    DatabaseReference myRef;
    TextView txtHomeTeamName,txtHomeTeamScore,txtHostTeamName,txtHostTeamScore,txtMatchTime;
    Button addMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Match Scores");//Database name


        txtHomeTeamName=findViewById(R.id.txtHomeTeamName);
        txtHomeTeamScore=findViewById(R.id.txtHomeTeamScore);
        txtHostTeamName=findViewById(R.id.txtHostTeamName);
        txtHostTeamScore=findViewById(R.id.txtHostTeamScore);
        txtMatchTime=findViewById(R.id.txtMatchTime);

        addMatch=findViewById(R.id.btnAddMatch);
        addMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HomeTeamName = txtHomeTeamName.getText().toString().trim();
                String HomeTeamScore = txtHomeTeamScore.getText().toString().trim();
                String HostTeamName = txtHostTeamName.getText().toString().trim();
                String HostTeamScore = txtHostTeamScore.getText().toString().trim();
                String MatchTime = txtMatchTime.getText().toString().trim();

                if(!TextUtils.isEmpty(HomeTeamName) && !TextUtils.isEmpty(HomeTeamScore)
                        && !TextUtils.isEmpty(HostTeamName)&& !TextUtils.isEmpty(HostTeamScore)
                        && !TextUtils.isEmpty(MatchTime)  ){

                    addMatch(HomeTeamName,HostTeamName,HomeTeamScore,HostTeamScore,MatchTime);

                }else {

                    Toast.makeText(getApplicationContext(),"Match details cannot be empty"
                            ,Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    public void addMatch(String HomeTeamName, String HostTeamName, String HomeTeamScore,
                         String HostTeamScore,String MatchTime){


        String id = myRef.push().getKey();
        Matches match = new Matches(id,HomeTeamName,HostTeamName,HomeTeamScore,HostTeamScore,MatchTime);
        myRef.child(id).setValue(match);


        Toast.makeText(this,"New match has added",Toast.LENGTH_SHORT).show();
    }
}