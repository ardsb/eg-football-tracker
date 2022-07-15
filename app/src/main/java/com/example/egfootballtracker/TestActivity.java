package com.example.egfootballtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.egfootballtracker.Adapter.TestAdapter;
import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.Services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {


    private RecyclerView recyclerViewTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recyclerViewTest=findViewById(R.id.recyclerViewTest);
        recyclerViewTest.setHasFixedSize(true);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<PlayerDetailsNew>> call = apiInterface.getPlayerDetails();
        call.enqueue(new Callback<List<PlayerDetailsNew>>() {
            @Override
            public void onResponse(Call<List<PlayerDetailsNew>> call, Response<List<PlayerDetailsNew>> response) {
            if(!response.isSuccessful()){
                Toast.makeText(TestActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                return;
            }

            List<PlayerDetailsNew> playerDetailsNewList = response.body();
                TestAdapter testAdapter = new TestAdapter(TestActivity.this, playerDetailsNewList);
                recyclerViewTest.setAdapter(testAdapter);

            }

            @Override
            public void onFailure(Call<List<PlayerDetailsNew>> call, Throwable t) {
                Toast.makeText(TestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}