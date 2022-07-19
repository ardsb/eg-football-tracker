package com.example.egfootballtracker.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.Model.PlayerDetailsNew;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.Services.ApiInterface;
import com.example.egfootballtracker.View.DisplayPlayersDetailsActivity;
import com.example.egfootballtracker.View.HomepageActivity;
import com.example.egfootballtracker.View.PlayerStatisticActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {


    List<PlayerDetailsNew> playerDetailsNewList;
    Context context;
    LinearLayout layout;
    public CircleImageView imageView;

    Button btnDelete;
    ApiInterface apiInterface;


    public TestAdapter(Context context,List<PlayerDetailsNew> playerDetailsNew){
        this.context = context;
        playerDetailsNewList = playerDetailsNew;


    }
    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.players_profile_list_layout,parent,false);

        return new TestViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PlayerDetailsNew playerDetailsNew = playerDetailsNewList.get(position);

        //For Profile
        holder.txtPLayersName.setText(playerDetailsNew.getPlayerName());
        holder.txtPlayerCountryName.setText(playerDetailsNew.getPlayerCountry());
        holder.txtPlayersAge.setText(playerDetailsNew.getPlayerAge());
        holder.txtPlayerBorn.setText(playerDetailsNew.getPlayerBorn());
        holder.txtPlayerPlayersHeight.setText(playerDetailsNew.getPlayerHeight());
        holder.txtPlayersPosition.setText(playerDetailsNew.getPlayerPosition());

        //For Player's Statistic
        holder.txtAppsStatistic.setText(playerDetailsNew.getPlayerApps());
        holder.txtMinutesStatistic.setText(playerDetailsNew.getPlayerPlayedMinutes());
        holder.txtGoalsStatistic.setText(playerDetailsNew.getPlayerGoals());
        holder.txtAssistStatistic.setText(playerDetailsNew.getPlayerAssist());
        holder.txtYelCardStatistic.setText(playerDetailsNew.getPlayerYellowCard());
        holder.txtRedCardStatistic.setText(playerDetailsNew.getPlayerRedCard());
        holder.txtSpGStatistic.setText(playerDetailsNew.getPlayerSpg());
        holder.txtPSStatistic.setText(playerDetailsNew.getPlayerPs());
        holder.txtArialsWonStatistic.setText(playerDetailsNew.getPlayerArialWon());
        holder.txtMotMStatistic.setText(playerDetailsNew.getPlayerMom());
        holder.txtProfilePerformance.setText(playerDetailsNew.getPlayerPerformance());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(context
                        , PlayerStatisticActivity.class);
                PlayerDetailsNew playerDetails=playerDetailsNewList.get(position);
                mainActivityIntent.putExtra("Player Details",playerDetails);
                context.startActivity(mainActivityIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return playerDetailsNewList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder{
        TextView txtPLayersName, txtPlayerCountryName,txtPlayersAge,txtPlayerBorn,
                txtPlayerPlayersHeight,txtPlayersPosition;

        TextView txtAppsStatistic,txtMinutesStatistic,txtGoalsStatistic,txtAssistStatistic,
                txtYelCardStatistic,txtRedCardStatistic,txtSpGStatistic,txtPSStatistic,
                txtArialsWonStatistic,txtMotMStatistic,txtProfilePerformance;



        public TestViewHolder(@NonNull View itemView) {
            super(itemView);


            //For Profile
            txtPLayersName = itemView.findViewById(R.id.txtDisplayingProfileName);
            txtPlayerCountryName = itemView.findViewById(R.id.txtDisplayingProfileCountry);
            txtPlayersAge = itemView.findViewById(R.id.txtDisplayingProfileAge);
            txtPlayerBorn = itemView.findViewById(R.id.txtDisplayingProfileBorn);
            txtPlayerPlayersHeight = itemView.findViewById(R.id.txtDisplayingProfileHeight);
            txtPlayersPosition = itemView.findViewById(R.id.txtDisplayingProfilePosition);
            layout = itemView.findViewById(R.id.playerProfileLayout);



            //For Player's Statistic
            txtAppsStatistic=itemView.findViewById(R.id.txtDisplayingProfileAppsStatistic);
            txtMinutesStatistic=itemView.findViewById(R.id.txtDisplayingProfileMinutedStatistic);
            txtGoalsStatistic=itemView.findViewById(R.id.txtDisplayingProfileGoalStatistic);
            txtAssistStatistic=itemView.findViewById(R.id.txtDisplayingProfileAssistStatistic);
            txtYelCardStatistic=itemView.findViewById(R.id.txtDisplayingProfileYelCardStatistic);
            txtRedCardStatistic=itemView.findViewById(R.id.txtDisplayingProfileRedCardStatistic);
            txtSpGStatistic=itemView.findViewById(R.id.txtDisplayingProfileSpgStatistic);
            txtPSStatistic=itemView.findViewById(R.id.txtDisplayingProfilePssStatistic);
            txtArialsWonStatistic=itemView.findViewById(R.id.txtDisplayingProfileArialWonStatistic);
            txtMotMStatistic=itemView.findViewById(R.id.txtDisplayingProfileMotMStatistic);
            txtProfilePerformance=itemView.findViewById(R.id.txtDisplayingProfilePerformance);




        }

        }
    }

