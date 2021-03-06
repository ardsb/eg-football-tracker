package com.example.egfootballtracker.View;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.Services.ApiInterface;
import com.google.firebase.database.DatabaseReference;

import java.io.File;
import java.io.FileOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayerStatisticActivity extends AppCompatActivity {

    TextView txtPlayerNameStatistic, txtPlayerAgeStatistic, txtPlayerBornStatistic,
            txtPlayerCountryStatistic, txtPlayerPositionStatistic,
            txtPlayerHeightStatistic; //For Profile

    TextView txtAppsStatisticPassing,txtMinutesStatisticPassing,txtGoalsStatisticPassing,
            txtAssistStatisticPassing, txtYelCardStatisticPassing,txtRedCardStatisticPassing,
            txtSpGStatisticPassing,txtPSSStatisticPassing,txtArialsStatisticPassing,
            txtMotMStatisticPassing,txtPlayerPerformanceStatisticPassing; //For Player's Statistic

    DatabaseReference myRef;
    Button btnDelete,btnEdit,btnDownloadData;
    CircleImageView imageView;
    ApiInterface apiInterface;
    Activity thisActivity;



    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_statistic);
        thisActivity =  this;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

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
        btnDelete =  findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
//        btnDownloadData = findViewById(R.id.btnDownloadData);

        txtPlayerNameStatistic.setText(playerDetails.getPlayerName());
        txtPlayerAgeStatistic.setText(playerDetails.getPlayerApps());
        txtPlayerBornStatistic.setText(playerDetails.getPlayerBorn());
        txtPlayerCountryStatistic.setText(playerDetails.getPlayerCountry());
        txtPlayerPositionStatistic.setText(playerDetails.getPlayerPosition());
        txtPlayerHeightStatistic.setText(playerDetails.getPlayerHeight());

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



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<Void> call = apiInterface.deletePlayer(playerDetails.getId());
                call.enqueue(new Callback<>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(thisActivity,"Player deletion successfull", Toast.LENGTH_SHORT).show();
                        thisActivity.finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerDetails.setPlayerName(txtPlayerNameStatistic.getText().toString());
                playerDetails.setPlayerAge(txtPlayerAgeStatistic.getText().toString());
                playerDetails.setPlayerBorn(txtPlayerBornStatistic.getText().toString());
                playerDetails.setPlayerCountry(txtPlayerCountryStatistic.getText().toString());
                playerDetails.setPlayerPosition(txtPlayerPositionStatistic.getText().toString());
                playerDetails.setPlayerHeight(txtPlayerHeightStatistic.getText().toString());
                playerDetails.setPlayerApps(txtAppsStatisticPassing.getText().toString());
                playerDetails.setPlayerPlayedMinutes(txtMinutesStatisticPassing.getText().toString());
                playerDetails.setPlayerGoals(txtGoalsStatisticPassing.getText().toString());
                playerDetails.setPlayerAssist(txtAssistStatisticPassing.getText().toString());
                playerDetails.setPlayerYellowCard(txtYelCardStatisticPassing.getText().toString());
                playerDetails.setPlayerRedCard(txtRedCardStatisticPassing.getText().toString());
                playerDetails.setPlayerSpg(txtSpGStatisticPassing.getText().toString());
                playerDetails.setPlayerPs(txtPSSStatisticPassing.getText().toString());
                playerDetails.setPlayerArialWon(txtArialsStatisticPassing.getText().toString());
                playerDetails.setPlayerMom(txtMotMStatisticPassing.getText().toString());
                playerDetails.setPlayerPerformance(txtPlayerPerformanceStatisticPassing.getText().toString());




                playerPerformanceCalculation();
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<PlayerDetails> call = apiInterface.editPlayer(playerDetails.getId(),playerDetails);
                call.enqueue(new Callback<>() {

                    @Override
                    public void onResponse(Call<PlayerDetails> call, Response<PlayerDetails> response) {
                        Toast.makeText(thisActivity,"Player Update Successful", Toast.LENGTH_SHORT).show();
                        thisActivity.finish();
                    }

                    @Override
                    public void onFailure(Call<PlayerDetails> call, Throwable t) {

                    }
                });
            }
        });

//        btnDownloadData.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                createMyPDF();
//            }
//        });
    }
    public void playerPerformanceCalculation(){

        int AppsStatistic,GoalsStaistic;
        double avg;

        AppsStatistic = Integer.parseInt(txtAppsStatisticPassing.getText().toString());
        GoalsStaistic = Integer.parseInt(txtGoalsStatisticPassing.getText().toString());
        avg=GoalsStaistic/AppsStatistic;
        txtSpGStatisticPassing.setText(String.valueOf(avg));



        if(avg >= 4)
        {
            txtPlayerPerformanceStatisticPassing.setText("Outstanding");
            Toast.makeText(PlayerStatisticActivity.this,
                    "Overall performance is Outstanding",
                    Toast.LENGTH_LONG).show();
        }
        else if(avg >= 2)
        {
            txtPlayerPerformanceStatisticPassing.setText("Excellent");
            Toast.makeText(PlayerStatisticActivity.this,
                    "Overall performance is Excellent",
                    Toast.LENGTH_LONG).show();
        }

        else if(avg >= 1)
        {
            txtPlayerPerformanceStatisticPassing.setText("Mediocre");
            Toast.makeText(PlayerStatisticActivity.this,
                    "Overall performance is Mediocre",
                    Toast.LENGTH_LONG).show();
        }


        else if(avg >= 0.5)
        {
            txtPlayerPerformanceStatisticPassing.setText("Average");
            Toast.makeText(PlayerStatisticActivity.this,
                    "Overall performance is Average",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            txtPlayerPerformanceStatisticPassing.setText("Poor");
            Toast.makeText(PlayerStatisticActivity.this,
                    "Overall performance is Poor",
                    Toast.LENGTH_LONG).show();
        }

    }
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void createMyPDF(){
//
//        PdfDocument myPdfDocument = new PdfDocument();
//        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
//        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
//
//
//
//        Paint myPaint = new Paint();
//        //For Profile
//        String PLayersName = txtPlayerNameStatistic.getText().toString().trim();
////        String PLayersAge = txtPLayersAge.getText().toString().trim();
////        String PLayersBorn = txtPLayersBorn.getText().toString().trim();
////        String PlayingCountry = txtPlayingCountry.getText().toString().trim();
////        String PlayersHeight = txtPlayersHeight.getText().toString().trim();
////        String PlayersPosition = txtPlayersPosition.getText().toString().trim();
////
////        //For Player's Statistics
////        String AppsStatistic = txtAppsStatistic.getText().toString().trim();
////        String MinutesStatistic = txtMinutesStatistic.getText().toString().trim();
////        String GoalsStaistic = txtGoalsStaistic.getText().toString().trim();
////        String AssistStatistic = txtAssistStatistic.getText().toString().trim();
////        String YelCardStatistic = txtYelCardStatistic.getText().toString().trim();
////        String RedCardStatistic = txtRedCardStatistic.getText().toString().trim();
////        String SpGStatistic = txtSpGStatistic.getText().toString().trim();
////        String PSStatistic = txtPSStatistic.getText().toString().trim();
////        String ArialsWonStatistic = txtArialsWonStatistic.getText().toString().trim();
////        String MotMStatistic = txtMotMStatistic.getText().toString().trim();
////        String PlayerPerfomanceStatistic = txtPlayerPerfomanceStatistic.getText().toString().trim();
//
//        int x = 10, y=25;
//
//        myPage.getCanvas().drawText
//                ( PLayersName, x, y, myPaint);
//        myPdfDocument.finishPage(myPage);
//
//        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/Players Details.pdf";
//        File myFile = new File(myFilePath);
//        try {
//            myPdfDocument.writeTo(new FileOutputStream(myFile));
//            Toast.makeText(PlayerStatisticActivity.this, "Data saved as pdf format", Toast.LENGTH_SHORT).show();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            txtPlayerNameStatistic.setText("ERROR");
//        }
//
//        myPdfDocument.close();
//    }
}