package com.example.egfootballtracker.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.Services.ApiInterface;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerStatisticActivity extends AppCompatActivity {

    TextView txtPlayerNameStatistic, txtPlayerAgeStatistic, txtPlayerBornStatistic,
            txtPlayerCountryStatistic, txtPlayerPositionStatistic,
            txtPlayerHeightStatistic; //For Profile

    TextView txtAppsStatisticPassing,txtMinutesStatisticPassing,txtGoalsStatisticPassing,
            txtAssistStatisticPassing, txtYelCardStatisticPassing,txtRedCardStatisticPassing,
            txtSpGStatisticPassing,txtPSSStatisticPassing,txtArialsStatisticPassing,
            txtMotMStatisticPassing,txtPlayerPerformanceStatisticPassing; //For Player's Statistic

    DatabaseReference myRef;
    Button btnDelete;
    CircleImageView imageView;
    ApiInterface apiInterface;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_statistic);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("player-details");

        PlayerDetailsNew playerDetails = (PlayerDetailsNew) getIntent().getSerializableExtra
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
        txtPlayerAgeStatistic.setText(playerDetails.getPlayerAge());
        txtPlayerBornStatistic.setText(playerDetails.getPlayerBorn());
        txtPlayerCountryStatistic.setText(playerDetails.getPlayerCountry());
        txtPlayerPositionStatistic.setText(playerDetails.getPlayerPosition());
        txtPlayerHeightStatistic.setText(playerDetails.getPlayerHeight());
//        Picasso.get()
//                .load(playerDetails.getmImageUrl())
//                .fit()
//                .centerCrop()
//                .into(imageView);


        //For Player's Statistic
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
        txtPlayerPerformanceStatisticPassing=findViewById(R.id.txtPlayerPerformanceStatisticPassing);


        txtAppsStatisticPassing.setText(playerDetails.getPlayerApps());
        txtMinutesStatisticPassing.setText(playerDetails.getPlayerPlayedMinutes());
        txtGoalsStatisticPassing.setText(playerDetails.getPlayerGoals());
        txtAssistStatisticPassing.setText(playerDetails.getPlayerAssist());
        txtYelCardStatisticPassing.setText(playerDetails.getPlayerYellowCard());
        txtRedCardStatisticPassing.setText(playerDetails.getPlayerRedCard());
        txtSpGStatisticPassing.setText(playerDetails.getPlayerSpg());
        txtPSSStatisticPassing.setText(playerDetails.getPlayerPs());
        txtArialsStatisticPassing.setText(playerDetails.getPlayerArialWon());
        txtMotMStatisticPassing.setText(playerDetails.getPlayerMom());
        txtPlayerPerformanceStatisticPassing.setText(playerDetails.getPlayerPerformance());




    }

}