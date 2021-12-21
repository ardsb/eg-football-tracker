package com.example.egfootballtracker.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPlayerDetailsActivity extends AppCompatActivity {
    DatabaseReference myRef;
    CircleImageView imageViewProfile;
    StorageReference storageRef;

    TextView txtPLayersName, txtPLayersAge, txtPLayersBorn, txtPlayingCountry, txtPlayerPlayingRole,
            txtMajorteam, txtPlayerBattingStyle, txtPlayerBowlingStyle;//For Profile

    TextView txtMatchesStatistic, txtInningsStatistic, txtRunsStaistic, txtHSStatistic,
            txtAverageStatistic, txtStrikeRateStatistic, txtHalfCenturyStatistic,
            txtCenturyStatistic, txtSixesStatistic, txtfoursStatistic;//For Batting Statistic
    ;

    TextView txtMatchesStatisticBowling, txtInningsStatisticBowling, txtBallsStaistic,
            txtWicketsStatistic, txtFourWicketsHaulStatistic, txtAverageStatisticBowling,
            txtFiveWicketsHaulStatistic, txtEconStatistic;//For Bowling Statistic
    Button addMatch, btnChooseFile;




    private String imageUploadUrl;
    private ProgressDialog uploadProgressDialog;

    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player_detail);


        storageRef = FirebaseStorage.getInstance().getReference("profile-images");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("player-details");

        //For Profile
        txtPLayersName = findViewById(R.id.txtPLayersName);
        txtPLayersAge = findViewById(R.id.txtPLayersAge);
        txtPLayersBorn = findViewById(R.id.txtPLayersBorn);
        txtPlayingCountry = findViewById(R.id.txtPlayingCountry);
        txtPlayerPlayingRole = findViewById(R.id.txtPlayerPlayingRole);
        txtMajorteam = findViewById(R.id.txtMajorteam);
        txtPlayerBattingStyle = findViewById(R.id.txtPlayerBattingStyle);
        txtPlayerBowlingStyle = findViewById(R.id.txtPlayerBowlingStyle);
        imageViewProfile = findViewById(R.id.imageViewProfile);

        //For Batting Statistic
        txtMatchesStatistic = findViewById(R.id.txtMatchesStatistic);
        txtInningsStatistic = findViewById(R.id.txtInningsStatistic);
        txtRunsStaistic = findViewById(R.id.txtRunsStaistic);
        txtHSStatistic = findViewById(R.id.txtHSStatistic);
        txtAverageStatistic = findViewById(R.id.txtAverageStatistic);
        txtStrikeRateStatistic = findViewById(R.id.txtStrikeRateStatistic);
        txtHalfCenturyStatistic = findViewById(R.id.txtHalfCenturyStatistic);
        txtCenturyStatistic = findViewById(R.id.txtCenturyStatistic);
        txtSixesStatistic = findViewById(R.id.txtSixesStatistic);
        txtfoursStatistic = findViewById(R.id.txtfoursStatistic);

        //For Bowling Statistic
        txtMatchesStatisticBowling = findViewById(R.id.txtMatchesStatisticBowling);
        txtInningsStatisticBowling = findViewById(R.id.txtInningsStatisticBowling);
        txtBallsStaistic = findViewById(R.id.txtBallsStaistic);
        txtWicketsStatistic = findViewById(R.id.txtWicketsStatistic);
        txtFourWicketsHaulStatistic = findViewById(R.id.txtFourWicketsHaulStatistic);
        txtFiveWicketsHaulStatistic = findViewById(R.id.txtFiveWicketsHaulStatistic);
        txtAverageStatisticBowling = findViewById(R.id.txtAverageStatisticBowling);
        txtEconStatistic = findViewById(R.id.txtEconStatistic);



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


        addMatch = findViewById(R.id.btnAdd);
        addMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fileUploader();


            }
        });

    }

    private void createPlayerOnFirebase() {

        //For Profile
        String PLayersName = txtPLayersName.getText().toString().trim();
        String PLayersAge = txtPLayersAge.getText().toString().trim();
        String PLayersBorn = txtPLayersBorn.getText().toString().trim();
        String PlayingCountry = txtPlayingCountry.getText().toString().trim();
        String PlayerPlayingRole = txtPlayerPlayingRole.getText().toString().trim();
        String Majorteam = txtMajorteam.getText().toString().trim();
        String PlayerBattingStyle = txtPlayerBattingStyle.getText().toString().trim();
        String PlayerBowlingStyle = txtPlayerBowlingStyle.getText().toString().trim();

        //For Batting Statistic
        String MatchesStatistic = txtMatchesStatistic.getText().toString().trim();
        String InningsStatistic = txtInningsStatistic.getText().toString().trim();
        String RunsStaistic = txtRunsStaistic.getText().toString().trim();
        String HSStatistic = txtHSStatistic.getText().toString().trim();
        String AverageStatistic = txtAverageStatistic.getText().toString().trim();
        String StrikeRateStatistic = txtStrikeRateStatistic.getText().toString().trim();
        String HalfCenturyStatisti = txtHalfCenturyStatistic.getText().toString().trim();
        String CenturyStatistic = txtCenturyStatistic.getText().toString().trim();
        String SixesStatistic = txtSixesStatistic.getText().toString().trim();
        String foursStatistic = txtfoursStatistic.getText().toString().trim();


        //For Bowling Statistic
        String MatchesStatisticBowling = txtMatchesStatisticBowling.getText().toString().trim();
        String InningsStatisticBowling = txtInningsStatisticBowling.getText().toString().trim();
        String BallsStaistic = txtBallsStaistic.getText().toString().trim();
        String WicketsStatistic = txtWicketsStatistic.getText().toString().trim();
        String FourWicketsHaulStatistic = txtFourWicketsHaulStatistic.getText().toString().trim();
        String FiveWicketsHaulStatistic = txtFiveWicketsHaulStatistic.getText().toString().trim();
        String AverageStatisticBowling = txtAverageStatisticBowling.getText().toString().trim();
        String EconStatistic = txtEconStatistic.getText().toString().trim();




        if
            //For Profile
        (!TextUtils.isEmpty(PLayersName) && !TextUtils.isEmpty(PLayersAge)
                && !TextUtils.isEmpty(PLayersBorn) && !TextUtils.isEmpty(PlayingCountry)
                && !TextUtils.isEmpty(PlayerPlayingRole) && !TextUtils.isEmpty(Majorteam)
                && !TextUtils.isEmpty(PlayerBattingStyle)
                && !TextUtils.isEmpty(PlayerBowlingStyle)

                //For Batting Statistic
                && !TextUtils.isEmpty(MatchesStatistic) && !TextUtils.isEmpty(InningsStatistic)
                && !TextUtils.isEmpty(RunsStaistic) && !TextUtils.isEmpty(HSStatistic)
                && !TextUtils.isEmpty(AverageStatistic)
                && !TextUtils.isEmpty(StrikeRateStatistic)
                && !TextUtils.isEmpty(HalfCenturyStatisti) && !TextUtils.isEmpty(CenturyStatistic)
                && !TextUtils.isEmpty(RunsStaistic) && !TextUtils.isEmpty(HSStatistic)
                && !TextUtils.isEmpty(SixesStatistic)
                && !TextUtils.isEmpty(foursStatistic)

                //For Bowling Statistic
                && !TextUtils.isEmpty(MatchesStatisticBowling)
                && !TextUtils.isEmpty(InningsStatisticBowling)
                && !TextUtils.isEmpty(BallsStaistic) && !TextUtils.isEmpty(WicketsStatistic)
                && !TextUtils.isEmpty(FourWicketsHaulStatistic)
                && !TextUtils.isEmpty(FiveWicketsHaulStatistic)
                && !TextUtils.isEmpty(AverageStatisticBowling)
                && !TextUtils.isEmpty(EconStatistic)) {

            addProfileDetails(PLayersName, PLayersAge, PLayersBorn, PlayingCountry,
                    PlayerPlayingRole, Majorteam, PlayerBattingStyle, PlayerBowlingStyle,
                    MatchesStatistic, InningsStatistic, RunsStaistic, HSStatistic,
                    AverageStatistic, StrikeRateStatistic, HalfCenturyStatisti, CenturyStatistic,
                    SixesStatistic, foursStatistic, MatchesStatisticBowling,InningsStatisticBowling,
                    BallsStaistic,WicketsStatistic, FourWicketsHaulStatistic,
                    FiveWicketsHaulStatistic,AverageStatisticBowling, EconStatistic);


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

                storageReference.putFile(imageUri).continueWithTask(new Continuation<UploadTask
                        .TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task)
                            throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (uploadProgressDialog.isShowing()) {
                            uploadProgressDialog.hide();
                        }

                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            Log.e("TEST", "then: " + downloadUri.toString());
                            Toast.makeText(AddPlayerDetailActivity.this
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
            , String PlayingCountry, String PlayerPlayingRole, String Majorteam
            , String PlayerBattingStyle, String PlayerBowlingStyle, String Matches, String Innings
            , String RunsBatting, String HS, String Ave, String SR, String HalfCentury
            , String Century, String Sixes, String Fours, String MatchesBowling
            , String inningsBowling, String balls, String wkts, String fourWicketsHaul
            , String fiveWicketsHaul, String aveBowling, String econ) {


        String id = myRef.push().getKey();
        PlayerDetails playerDetails = new PlayerDetails(id, imageUploadUrl, PLayersName, PLayersAge
                , PLayersBorn, PlayingCountry, PlayerPlayingRole, Majorteam, PlayerBattingStyle
                , PlayerBowlingStyle, Matches, Innings, RunsBatting, HS, Ave, SR, HalfCentury
                , Century, Sixes, Fours,MatchesBowling,inningsBowling,balls,wkts,fourWicketsHaul
                ,fiveWicketsHaul,aveBowling,econ);

        myRef.child(id).setValue(playerDetails);
    }
}