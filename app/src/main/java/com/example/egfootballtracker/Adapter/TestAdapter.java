package com.example.egfootballtracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {


    List<PlayerDetailsNew> playerDetailsNewList;
    Context context;

    public TestAdapter(Context context,List<PlayerDetailsNew> playerDetailsNew){
        this.context = context;
        playerDetailsNewList = playerDetailsNew;
    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {

        PlayerDetailsNew playerDetailsNew = playerDetailsNewList.get(position);
        holder.playerName.setText(playerDetailsNew.getPlayerName());
        holder.PlayerAge.setText(playerDetailsNew.getPlayerAge());
        holder.PlayerCountry.setText(playerDetailsNew.getPlayerCountry());
    }

    @Override
    public int getItemCount() {
        return playerDetailsNewList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder{
        TextView playerName,PlayerAge,PlayerCountry;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);

            playerName = itemView.findViewById(R.id.PlayerName);
            PlayerAge = itemView.findViewById(R.id.PlayerAge);
            PlayerCountry = itemView.findViewById(R.id.PlayerCountry);



        }
    }
}
