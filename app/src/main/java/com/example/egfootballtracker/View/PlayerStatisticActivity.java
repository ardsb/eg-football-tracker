package com.example.egfootballtracker.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerStatisticActivity extends AppCompatActivity {

    TextView txtPlayerNameStatistic, txtPlayerAgeStatistic, txtPlayerBornStatistic,
            txtPlayerCountryStatistic, txtPlayerPositionStatistic,
            txtPlayerHeightStatistic; //For Profile

    TextView txtAppsStatisticPassing,txtMinutesStatisticPassing,txtGoalsStatisticPassing,
            txtAssistStatisticPassing, txtYelCardStatisticPassing,txtRedCardStatisticPassing,
            txtSpGStatisticPassing,txtPSSStatisticPassing,txtArialsStatisticPassing,
            txtMotMStatisticPassing; //For Player's Statistic



    DatabaseReference myRef;
    Button btndelete;
    CircleImageView imageView;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_statistic);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("player-details");

        PlayerDetails playerDetails = (PlayerDetails) getIntent().getSerializableExtra
                ("Player Details");



        //For Profile
        txtPlayerNameStatistic = findViewById(R.id.txtPlayerNameStatistic);
        txtPlayerAgeStatistic = findViewById(R.id.txtPlayerAgeStatistic);
        txtPlayerBornStatistic = findViewById(R.id.txtPlayerBornStatistic);
        txtPlayerCountryStatistic = findViewById(R.id.txtPlayerCountryStatistic);
        txtPlayerPositionStatistic = findViewById(R.id.txtPlayerPositionStatistic);
        txtPlayerHeightStatistic = findViewById(R.id.txtPlayerHeightStatistic);
        imageView=findViewById(R.id.imgProfileDisplayListStatistic);

        txtPlayerNameStatistic.setText(playerDetails.getPlayerName());
        txtPlayerAgeStatistic.setText(playerDetails.getCurrentAge());
        txtPlayerBornStatistic.setText(playerDetails.getBorn());
        txtPlayerCountryStatistic.setText(playerDetails.getCountry());
        txtPlayerPositionStatistic.setText(playerDetails.getPosition());
        txtPlayerHeightStatistic.setText(playerDetails.getHeight());
        Picasso.get()
                .load(playerDetails.getmImageUrl())
                .fit()
                .centerCrop()
                .into(imageView);


        //For Batting Statistic
        txtAppsStatisticPassing = findViewById(R.id.txtAppsStatisticPassing);
        txtMinutesStatisticPassing = findViewById(R.id.txtMinutesStatisticPassing);
        txtGoalsStatisticPassing = findViewById(R.id.txtGoalsStatisticPassing);
        txtAssistStatisticPassing = findViewById(R.id.txtAssistStatisticPassing);
        txtYelCardStatisticPassing = findViewById(R.id.txtYelCardStatisticPassing);
        txtRedCardStatisticPassing = findViewById(R.id.txtRedCardStatisticPassing);
        txtSpGStatisticPassing = findViewById(R.id.txtSpGStatisticPassing);
        txtPSSStatisticPassing = findViewById(R.id.txtPSSStatisticPassing);
        txtArialsStatisticPassing=findViewById(R.id.txtArialsStatisticPassing);
        txtMotMStatisticPassing=findViewById(R.id.txtMotMStatisticPassing);


        txtAppsStatisticPassing.setText(playerDetails.getApps());
        txtMinutesStatisticPassing.setText(playerDetails.getMinutes());
        txtGoalsStatisticPassing.setText(playerDetails.getGoals());
        txtAssistStatisticPassing.setText(playerDetails.getAssist());
        txtYelCardStatisticPassing.setText(playerDetails.getYelCard());
        txtRedCardStatisticPassing.setText(playerDetails.getRedCard());
        txtSpGStatisticPassing.setText(playerDetails.getSpg());
        txtPSSStatisticPassing.setText(playerDetails.getPss());
        txtArialsStatisticPassing.setText(playerDetails.getArialWon());
        txtMotMStatisticPassing.setText(playerDetails.getMotM());



        btndelete=findViewById(R.id.btnDelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayerStatisticActivity.this, "One player has deleted ",
                        Toast.LENGTH_SHORT).show();
                DatabaseReference dR = FirebaseDatabase.getInstance().getReference
                        ("player-details").child(playerDetails.getId());
                dR.removeValue();
            }
        });

    }

}