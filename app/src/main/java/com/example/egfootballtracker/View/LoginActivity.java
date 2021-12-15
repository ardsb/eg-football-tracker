package com.example.egfootballtracker.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egfootballtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView input, input2, registerPage;
    Button login;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    private String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        rememberMe = findViewById(R.id.checkBox);

//      Initialize shared preferences
        sharedPreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        final String input_email = "arkam.ardsb@gmail.com";
        final String input_password = "123456";

        input = findViewById(R.id.input_email);
        input.setText(input_email);
        input2 = findViewById(R.id.input_password);
        input2.setText(input_password);
        registerPage = findViewById(R.id.btnRegister);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = input.getText().toString();
                String getPassword = input2.getText().toString();


                if (rememberMe.isChecked()) {

                    Toast.makeText(LoginActivity.this,
                            "Your login credential has successfully saved",
                            Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Name", getName);
                    editor.putString("Password", getPassword);
                    editor.commit();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Your login credential has not saved", Toast.LENGTH_SHORT).show();
                }
                String email = input.getText().toString();
                String password = input2.getText().toString();

                signInUser(email, password);



            }
        });




    }

    public void signInUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Welcome to firebase"
                                            + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                            Intent main = new Intent(LoginActivity.this,
                                    HomepageActivity.class);
                            startActivity(main);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed."
                                    , Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });


    }


}