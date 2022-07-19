package com.example.egfootballtracker.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.R;

import com.example.egfootballtracker.Services.ApiInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPlayerDetailsActivity extends AppCompatActivity {
    DatabaseReference myRef;
    CircleImageView imageViewProfile;
    StorageReference storageRef;
    TextView responseTV;

    TextView txtPLayersName, txtPLayersAge, txtPLayersBorn, txtPlayingCountry, txtPlayersHeight,
            txtPlayersPosition;//For Profile

    TextView txtAppsStatistic, txtMinutesStatistic, txtGoalsStaistic, txtAssistStatistic,
            txtYelCardStatistic, txtRedCardStatistic, txtSpGStatistic,
            txtPSStatistic, txtArialsWonStatistic, txtMotMStatistic
            ,txtPlayerPerfomanceStatistic;//For player's Statistics
    ;
    Button addPlayerDetails, btnChooseFile, btnCalculatePerformance,btnInfo;

    private String imageUploadUrl;
    private ProgressDialog uploadProgressDialog;

    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player_details);

        ActivityCompat.requestPermissions(AddPlayerDetailsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        storageRef = FirebaseStorage.getInstance().getReference("profile-images");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("player-details");

        //For Profile
        txtPLayersName = findViewById(R.id.txtPLayersName);
        txtPLayersAge = findViewById(R.id.txtPLayersAge);
        txtPLayersBorn = findViewById(R.id.txtPLayersBorn);
        txtPlayingCountry = findViewById(R.id.txtPlayersCountry);
        txtPlayersHeight = findViewById(R.id.txtPlayersHeight);
        txtPlayersPosition = findViewById(R.id.txtPlayersPosition);
        imageViewProfile = findViewById(R.id.imageViewProfile);

        //For PLayer's Statistics
        txtAppsStatistic = findViewById(R.id.txtAppsStatistic);
        txtMinutesStatistic = findViewById(R.id.txtMinutesStatistic);
        txtGoalsStaistic = findViewById(R.id.txtGoalsStaistic);
        txtAssistStatistic = findViewById(R.id.txtAssistStatistic);
        txtYelCardStatistic = findViewById(R.id.txtYelCardStatistic);
        txtRedCardStatistic = findViewById(R.id.txtRedCardStatistic);
        txtSpGStatistic = findViewById(R.id.txtSpGStatistic);
        txtPSStatistic = findViewById(R.id.txtPSStatistic);
        txtArialsWonStatistic = findViewById(R.id.txtArialsWonStatistic);
        txtMotMStatistic = findViewById(R.id.txtMotMStatistic);
        txtPlayerPerfomanceStatistic = findViewById(R.id.txtPlayerPerfomanceStatistic);


        uploadProgressDialog = new ProgressDialog(this);
        uploadProgressDialog.setTitle("Adding this player into to the system");
        uploadProgressDialog.setMessage("Please hold tight while we upload the players details");
        uploadProgressDialog.setCancelable(false);
        uploadProgressDialog.setCanceledOnTouchOutside(false);


        btnChooseFile = findViewById(R.id.btnChooseFile);

        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChoose();

            }
        });

        btnCalculatePerformance = findViewById(R.id.btnCalculatePerformance);
        btnCalculatePerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerPerformanceCalculation();
            }
        });


        addPlayerDetails = findViewById(R.id.btnAdd);
        addPlayerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.

                if (txtPLayersName.getText().toString().isEmpty() ||
                        txtPLayersAge.getText().toString().isEmpty() ||
                        txtPLayersBorn.getText().toString().isEmpty() ||
                        txtPlayingCountry.getText().toString().isEmpty() ||
                        txtPlayersHeight.getText().toString().isEmpty() ||
                        txtPlayersPosition.getText().toString().isEmpty() ||

                        //For Player's Statistics
                        txtAppsStatistic.getText().toString().isEmpty() ||
                        txtMinutesStatistic.getText().toString().isEmpty() ||
                        txtGoalsStaistic.getText().toString().isEmpty() ||
                        txtAssistStatistic.getText().toString().isEmpty() ||
                        txtYelCardStatistic.getText().toString().isEmpty() ||
                        txtRedCardStatistic.getText().toString().isEmpty() ||
                        txtSpGStatistic.getText().toString().isEmpty() ||
                        txtPSStatistic.getText().toString().isEmpty() ||
                        txtArialsWonStatistic.getText().toString().isEmpty() ||
                        txtMotMStatistic.getText().toString().isEmpty() ||
                        txtPlayerPerfomanceStatistic.getText().toString().isEmpty()) {
                    Toast.makeText(AddPlayerDetailsActivity.this,
                            "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }

                postData(txtPLayersName.getText().toString().trim(),
                        txtPLayersAge.getText().toString().trim(),
                        txtPLayersBorn.getText().toString().trim(),
                        txtPlayingCountry.getText().toString().trim(),
                        txtPlayersHeight.getText().toString().trim(),
                        txtPlayersPosition.getText().toString().trim(),

                //For Player's Statistics
                        txtAppsStatistic.getText().toString().trim(),
                        txtMinutesStatistic.getText().toString().trim(),
                        txtGoalsStaistic.getText().toString().trim(),
                        txtAssistStatistic.getText().toString().trim(),
                        txtYelCardStatistic.getText().toString().trim(),
                        txtRedCardStatistic.getText().toString().trim(),
                        txtSpGStatistic.getText().toString().trim(),
                        txtPSStatistic.getText().toString().trim(),
                        txtArialsWonStatistic.getText().toString().trim(),
                        txtMotMStatistic.getText().toString().trim(),
                        txtPlayerPerfomanceStatistic.getText().toString().trim());
                fileUploader();
            }

        });

        btnInfo=findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                createMyPDF();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createMyPDF(){

        PdfDocument myPdfDocument = new PdfDocument();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);



        Paint myPaint = new Paint();
        //For Profile
        String PLayersName = txtPLayersName.getText().toString().trim();
        String PLayersAge = txtPLayersAge.getText().toString().trim();
        String PLayersBorn = txtPLayersBorn.getText().toString().trim();
        String PlayingCountry = txtPlayingCountry.getText().toString().trim();
        String PlayersHeight = txtPlayersHeight.getText().toString().trim();
        String PlayersPosition = txtPlayersPosition.getText().toString().trim();

        //For Player's Statistics
        String AppsStatistic = txtAppsStatistic.getText().toString().trim();
        String MinutesStatistic = txtMinutesStatistic.getText().toString().trim();
        String GoalsStaistic = txtGoalsStaistic.getText().toString().trim();
        String AssistStatistic = txtAssistStatistic.getText().toString().trim();
        String YelCardStatistic = txtYelCardStatistic.getText().toString().trim();
        String RedCardStatistic = txtRedCardStatistic.getText().toString().trim();
        String SpGStatistic = txtSpGStatistic.getText().toString().trim();
        String PSStatistic = txtPSStatistic.getText().toString().trim();
        String ArialsWonStatistic = txtArialsWonStatistic.getText().toString().trim();
        String MotMStatistic = txtMotMStatistic.getText().toString().trim();
        String PlayerPerfomanceStatistic = txtPlayerPerfomanceStatistic.getText().toString().trim();

        int x = 10, y=25;

            myPage.getCanvas().drawText
                    ("Player name:" +" "+ PLayersName, x, y, myPaint);






        myPdfDocument.finishPage(myPage);

        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/Players Details.pdf";
        File myFile = new File(myFilePath);
        try {
            myPdfDocument.writeTo(new FileOutputStream(myFile));
            Toast.makeText(AddPlayerDetailsActivity.this, "Data saved as pdf format", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            txtPLayersName.setText("ERROR");
        }

        myPdfDocument.close();
    }

        private void postData(String PLayersName, String PlayerAge, String PlayerBorn,
                              String PlayersCountry, String PlayersHeight, String PlayersPosition,
                              String PlayersApps, String PlayersMinutes, String PlayersGoals,
                              String PlayersAssist, String PlayersYelCard, String PlayersRedCard,
                              String PlayerSpg, String PlayersPS, String PlayersArialsWon,
                              String PlayersMotM,String PlayersPerformance) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.3.2:8080/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);

            PlayerDetailsNew playerDetailsNew = new PlayerDetailsNew(PLayersName, PlayerAge,
                    PlayerBorn,PlayersCountry,PlayersHeight,PlayersPosition,PlayersApps,PlayersMinutes,PlayersGoals,
                    PlayersAssist,PlayersYelCard,PlayersRedCard,PlayerSpg,PlayersPS,PlayersArialsWon,
                    PlayersMotM,PlayersPerformance);

            Call<PlayerDetailsNew> call = retrofitAPI.setPlayerDetails(playerDetailsNew);

            call.enqueue(new Callback<PlayerDetailsNew>() {
                @Override
                public void onResponse(Call<PlayerDetailsNew> call, Response<PlayerDetailsNew> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(AddPlayerDetailsActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddPlayerDetailsActivity.this, "Request Fail", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<PlayerDetailsNew> call, Throwable t) {
                    responseTV.setText("Error found is : " + t.getMessage());
                }
            });


}





    private void createPlayerOnFirebase() {

        //For Profile
        String PLayersName = txtPLayersName.getText().toString().trim();
        String PLayersAge = txtPLayersAge.getText().toString().trim();
        String PLayersBorn = txtPLayersBorn.getText().toString().trim();
        String PlayingCountry = txtPlayingCountry.getText().toString().trim();
        String PlayersHeight = txtPlayersHeight.getText().toString().trim();
        String PlayersPosition = txtPlayersPosition.getText().toString().trim();

        //For Player's Statistics
        String AppsStatistic = txtAppsStatistic.getText().toString().trim();
        String MinutesStatistic = txtMinutesStatistic.getText().toString().trim();
        String GoalsStaistic = txtGoalsStaistic.getText().toString().trim();
        String AssistStatistic = txtAssistStatistic.getText().toString().trim();
        String YelCardStatistic = txtYelCardStatistic.getText().toString().trim();
        String RedCardStatistic = txtRedCardStatistic.getText().toString().trim();
        String SpGStatistic = txtSpGStatistic.getText().toString().trim();
        String PSStatistic = txtPSStatistic.getText().toString().trim();
        String ArialsWonStatistic = txtArialsWonStatistic.getText().toString().trim();
        String MotMStatistic = txtMotMStatistic.getText().toString().trim();
        String PlayerPerfomanceStatistic = txtPlayerPerfomanceStatistic.getText().toString().trim();

        if
            //For Profile
        (!TextUtils.isEmpty(PLayersName) && !TextUtils.isEmpty(PLayersAge)
                && !TextUtils.isEmpty(PLayersBorn) && !TextUtils.isEmpty(PlayingCountry)
                && !TextUtils.isEmpty(PlayersHeight) && !TextUtils.isEmpty(PlayersPosition)


                //For Player's Statistic
                && !TextUtils.isEmpty(AppsStatistic) && !TextUtils.isEmpty(MinutesStatistic)
                && !TextUtils.isEmpty(GoalsStaistic) && !TextUtils.isEmpty(AssistStatistic)
                && !TextUtils.isEmpty(YelCardStatistic)
                && !TextUtils.isEmpty(RedCardStatistic)
                && !TextUtils.isEmpty(SpGStatistic) && !TextUtils.isEmpty(PSStatistic)
                && !TextUtils.isEmpty(ArialsWonStatistic) && !TextUtils.isEmpty(MotMStatistic) &&
                !TextUtils.isEmpty(PlayerPerfomanceStatistic) ){

            addProfileDetails(PLayersName, PLayersAge, PLayersBorn, PlayingCountry,
                    PlayersHeight, PlayersPosition, AppsStatistic, MinutesStatistic,
                    GoalsStaistic, AssistStatistic, YelCardStatistic, RedCardStatistic,
                    SpGStatistic, PSStatistic, ArialsWonStatistic, MotMStatistic,PlayerPerfomanceStatistic);


        } else {

            Toast.makeText(getApplicationContext(), "Player details cannot be empty"
                    , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageViewProfile.setImageURI(imageUri);

        }
    }

    private String getExtention(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    public void fileUploader() {

        if (imageUri != null) {
            StorageReference storageReference = storageRef.child(System.currentTimeMillis()
                    + "." + getExtention(imageUri));

            if (imageUri != null) {
                if (!uploadProgressDialog.isShowing()) {
                    uploadProgressDialog.show();
                }

                storageReference.putFile(imageUri).continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (uploadProgressDialog.isShowing()) {
                            uploadProgressDialog.hide();
                        }

                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            Log.e("TEST", "then: " + downloadUri.toString());
                            Toast.makeText(AddPlayerDetailsActivity.this
                                    , "New Player Has Added", Toast.LENGTH_LONG)
                                    .show();
                            imageUploadUrl = downloadUri.toString();

                            createPlayerOnFirebase();


                        } else {

                            Toast.makeText(getApplicationContext(), "upload failed: "
                                    + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        } else {

            if (uploadProgressDialog.isShowing()) {
                uploadProgressDialog.hide();
            }
            Toast.makeText(this, "No file selected", Toast.LENGTH_LONG).show();
        }

    }

    public void fileChoose() {

        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    public void addProfileDetails(String PLayersName, String PLayersAge, String PLayersBorn
            , String PlayingCountry, String PlayersHeight, String PlayersPosition, String AppsStatistic,
                                  String MinutesStatistic, String GoalsStaistic, String AssistStatistic,
                                  String YelCardStatistic, String RedCardStatistic,
                                  String SpGStatistic, String PSStatistic,
                                  String ArialsWonStatistic, String MotMStatistic, String PlayerPerfomanceStatistic) {


        String id = myRef.push().getKey();
        PlayerDetails playerDetails = new PlayerDetails(id, imageUploadUrl, PLayersName, PLayersAge
                , PLayersBorn, PlayingCountry, PlayersHeight, PlayersPosition, AppsStatistic
                , MinutesStatistic,  GoalsStaistic, AssistStatistic, YelCardStatistic, RedCardStatistic,
                SpGStatistic, PSStatistic,ArialsWonStatistic,MotMStatistic,PlayerPerfomanceStatistic);

        myRef.child(id).setValue(playerDetails);
    }

    public void playerPerformanceCalculation(){

        int AppsStatistic,GoalsStaistic;
        double avg;

        AppsStatistic = Integer.parseInt(txtAppsStatistic.getText().toString());
        GoalsStaistic = Integer.parseInt(txtGoalsStaistic.getText().toString());
        avg=GoalsStaistic/AppsStatistic;
        txtSpGStatistic.setText(String.valueOf(avg));



        if(avg >= 4)
        {
            txtPlayerPerfomanceStatistic.setText("Outstanding");
            Toast.makeText(AddPlayerDetailsActivity.this,
                    "Overall performance is Outstanding",
                    Toast.LENGTH_LONG).show();
        }
        else if(avg >= 2)
        {
            txtPlayerPerfomanceStatistic.setText("Excellent");
            Toast.makeText(AddPlayerDetailsActivity.this,
                    "Overall performance is Excellent",
                    Toast.LENGTH_LONG).show();
        }

        else if(avg >= 1)
        {
            txtPlayerPerfomanceStatistic.setText("Mediocre");
            Toast.makeText(AddPlayerDetailsActivity.this,
                    "Overall performance is Mediocre",
                    Toast.LENGTH_LONG).show();
        }


        else if(avg >= 0.5)
        {
            txtPlayerPerfomanceStatistic.setText("Average");
            Toast.makeText(AddPlayerDetailsActivity.this,
                    "Overall performance is Average",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            txtPlayerPerfomanceStatistic.setText("Poor");
            Toast.makeText(AddPlayerDetailsActivity.this,
                    "Overall performance is Poor",
                    Toast.LENGTH_LONG).show();
        }

    }

    void showInfoDialog() {
        final Dialog dialog = new Dialog(AddPlayerDetailsActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_info);
        dialog.show();
    }

}