    package com.example.egfootballtracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.egfootballtracker.Model.PlayerDetails;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.View.PlayerStatisticActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayersProfileAdpater extends RecyclerView.Adapter<PlayersProfileAdpater.ViewHolder> {
    private List<PlayerDetails> dataSet;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder   {

        LinearLayout layout;

        TextView txtPLayersName, txtprofileCountryName,txtPLayersAge,txtProfileBorn,txtPlayerPlayersHeight
                ,txtPlayersPosition;

        TextView txtAppsStatistic,txtMinutesStatistic,txtGoalsStatistic,txtAssistStatistic,
                txtYelCardStatistic,txtRedCardStatistic,txtSpGStatistic,txtPSStatistic,
                txtArialsWonStatistic,txtMotMStatistic;
                    //For Player's Statistic


       public CircleImageView imageView;

        public ViewHolder(View view) {
            super(view);

            //For Profile
            txtPLayersName = view.findViewById(R.id.txtDisplayingProfileName);
            txtprofileCountryName = view.findViewById(R.id.txtDisplayingProfileCountry);
            txtPLayersAge = view.findViewById(R.id.txtDisplayingProfileAge);
            txtProfileBorn = view.findViewById(R.id.txtDisplayingProfileBorn);
            txtPlayerPlayersHeight = view.findViewById(R.id.txtDisplayingProfileHeight);
            txtPlayersPosition = view.findViewById(R.id.txtDisplayingProfilePosition);
            layout = view.findViewById(R.id.playerProfileLayout);
            imageView =view.findViewById(R.id.imgProfileImageList);

            //For Player's Statistic
            txtAppsStatistic=view.findViewById(R.id.txtDisplayingProfileAppsStatistic);
            txtMinutesStatistic=view.findViewById(R.id.txtDisplayingProfileMinutedStatistic);
            txtGoalsStatistic=view.findViewById(R.id.txtDisplayingProfileGoalStatistic);
            txtAssistStatistic=view.findViewById(R.id.txtDisplayingProfileAssistStatistic);
            txtYelCardStatistic=view.findViewById(R.id.txtDisplayingProfileYelCardStatistic);
            txtRedCardStatistic=view.findViewById(R.id.txtDisplayingProfileRedCardStatistic);
            txtSpGStatistic=view.findViewById(R.id.txtDisplayingProfileSpgStatistic);
            txtPSStatistic=view.findViewById(R.id.txtDisplayingProfilePssStatistic);
            txtArialsWonStatistic=view.findViewById(R.id.txtDisplayingProfileArialWonStatistic);
            txtMotMStatistic=view.findViewById(R.id.txtDisplayingProfileMotMStatistic);







        }
    }

    public PlayersProfileAdpater(Context context, List<PlayerDetails> dataSet) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.players_profile_list_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        PlayerDetails playerDetails = dataSet.get(position);

        //For Profile
        viewHolder.txtPLayersName.setText(dataSet.get(position).getPlayerName());
        viewHolder.txtprofileCountryName.setText(dataSet.get(position).getCountry());
        viewHolder.txtPLayersAge.setText(dataSet.get(position).getCurrentAge());
        viewHolder.txtProfileBorn.setText(dataSet.get(position).getBorn());
        viewHolder.txtPlayerPlayersHeight.setText(dataSet.get(position).getHeight());
        viewHolder.txtPlayersPosition.setText(dataSet.get(position).getPosition());



        //For Player's Statistic
        viewHolder.txtAppsStatistic.setText(dataSet.get(position).getApps());
        viewHolder.txtMinutesStatistic.setText(dataSet.get(position).getMinutes());
        viewHolder.txtGoalsStatistic.setText(dataSet.get(position).getGoals());
        viewHolder.txtAssistStatistic.setText(dataSet.get(position).getAssist());
        viewHolder.txtYelCardStatistic.setText(dataSet.get(position).getYelCard());
        viewHolder.txtRedCardStatistic.setText(dataSet.get(position).getRedCard());
        viewHolder.txtSpGStatistic.setText(dataSet.get(position).getSpg());
        viewHolder.txtPSStatistic.setText(dataSet.get(position).getPss());
        viewHolder.txtArialsWonStatistic.setText(dataSet.get(position).getArialWon());
        viewHolder.txtMotMStatistic.setText(dataSet.get(position).getMotM());




        String image = playerDetails.getmImageUrl();
        if (image !=null && !image.trim().equals("")) {
            Picasso.get()
                    .load(image).into(viewHolder.imageView);
        }


        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(context
                        , PlayerStatisticActivity.class);
                PlayerDetails playerDetails=dataSet.get(position);
                mainActivityIntent.putExtra("Player Details",playerDetails);
                context.startActivity(mainActivityIntent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
