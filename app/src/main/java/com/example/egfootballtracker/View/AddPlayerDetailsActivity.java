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

    TextView txtPLayersName, txtPLayersAge, txtPLayersBorn, txtPlayingCountry, txtPlayersHeight,
            txtPlayersPosition;//For Profile

    TextView txtAppsStatistic, txtMinutesStatistic, txtGoalsStaistic, txtAssistStatistic,
            txtYelCardStatistic, txtRedCardStatistic, txtSpGStatistic,
            txtPSStatistic, txtArialsWonStatistic, txtMotMStatistic;//For player's Statistics
    ;
    Button addPlayerDetails, btnChooseFile;

    private String imageUploadUrl;
    private ProgressDialog uploadProgressDialog;

    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player_details);


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

        addPlayerDetails = findViewById(R.id.btnAdd);
        addPlayerDetails.setOnClickListener(new View.OnClickListener() {
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
                && !TextUtils.isEmpty(ArialsWonStatistic) && !TextUtils.isEmpty(MotMStatistic)){

            addProfileDetails(PLayersName, PLayersAge, PLayersBorn, PlayingCountry,
                    PlayersHeight, PlayersPosition, AppsStatistic, MinutesStatistic,
                    GoalsStaistic, AssistStatistic, YelCardStatistic, RedCardStatistic,
                    SpGStatistic, PSStatistic, ArialsWonStatistic, MotMStatistic);


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
                                  String ArialsWonStatistic, String MotMStatistic) {


        String id = myRef.push().getKey();
        PlayerDetails playerDetails = new PlayerDetails(id, imageUploadUrl, PLayersName, PLayersAge
                , PLayersBorn, PlayingCountry, PlayersHeight, PlayersPosition, AppsStatistic
                , MinutesStatistic,  GoalsStaistic, AssistStatistic, YelCardStatistic, RedCardStatistic,
                SpGStatistic, PSStatistic,ArialsWonStatistic,MotMStatistic);

        myRef.child(id).setValue(playerDetails);
    }
}