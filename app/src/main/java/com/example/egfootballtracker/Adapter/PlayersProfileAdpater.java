//    package com.example.egfootballtracker.Adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.egfootballtracker.Model.PlayerDetails;
//import com.example.egfootballtracker.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class PlayersProfileAdpater extends RecyclerView.Adapter<PlayersProfileAdpater.ViewHolder> {
//    private List<PlayerDetails> dataSet;
//    private Context context;
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder   {
//
//        LinearLayout layout;
//
//        TextView txtPLayersName, profileCountryName,txtPLayersAge,txtProfileBorn,txtPlayerPlayersHeight
//                ,txtPlayersPosition;
//
//        TextView txtAppsStatistic,txtMinutesStatistic,txtGoalsStatistic,txtAssistStatistic,
//                txtYelCardStatistic,txtRedCardStatistic,txtSpGStatistic,txtPSStatistic,
//                txtArialsWonStatistic,txtMotMStatistic;
//                    //For Player's Statistic
//
//
//       public CircleImageView imageView;
//
//        public ViewHolder(View view) {
//            super(view);
//
//            //For Profile
//            txtPLayersName = view.findViewById(R.id.txtPLayersName);
//            profileCountryName = view.findViewById(R.id.txtProfileCountryNameStatisticAdmin);
//            txtPLayersAge = view.findViewById(R.id.txtPLayersAge);
//            txtProfileBorn = view.findViewById(R.id.txtProfileBornAdmin);
//            txtPlayerPlayersHeight = view.findViewById(R.id.txtPlayerPlayersHeight);
//            txtPlayersPosition = view.findViewById(R.id.txtPlayersPosition);
//            layout = view.findViewById(R.id.playerProfileLayout);
//            imageView =view.findViewById(R.id.imgProfileImageList);
//
//            //For Player's Statistic
//            txtAppsStatistic=view.findViewById(R.id.txtAppsStatistic);
//            txtMinutesStatistic=view.findViewById(R.id.txtMinutesStatistic);
//            txtGoalsStatistic=view.findViewById(R.id.txtGoalsStatistic);
//            txtAssistStatistic=view.findViewById(R.id.txtAssistStatistic);
//            txtYelCardStatistic=view.findViewById(R.id.txtYelCardStatistic);
//            txtRedCardStatistic=view.findViewById(R.id.txtRedCardStatistic);
//            txtSpGStatistic=view.findViewById(R.id.txtSpGStatistic);
//            txtPSStatistic=view.findViewById(R.id.txtPSStatistic);
//            txtArialsWonStatistic=view.findViewById(R.id.txtArialsWonStatistic);
//            txtMotMStatistic=view.findViewById(R.id.txtMotMStatistic);
//
//
//            //For Bowling Statistic
//            txtMatchesBowling=view.findViewById(R.id.txtMatchesBowling);
//            txtInningsBowling=view.findViewById(R.id.txtInningsBowling);
//            txtBalls=view.findViewById(R.id.txtBalls);
//            txtWkts=view.findViewById(R.id.txtWkts);
//            txt4w=view.findViewById(R.id.txt4w);
//            txt5w=view.findViewById(R.id.txt5w);
//            txtAveBowling=view.findViewById(R.id.txtAveBowling);
//            txtEcon=view.findViewById(R.id.txtEcon);
//
//
//
//
//        }
//    }
//
//    public PlayersProfileAdpater(Context context, List<PlayerDetails> dataSet) {
//        this.dataSet = dataSet;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.players_profile_list_layout, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//
//        PlayerDetails playerDetails = dataSet.get(position);
//
//        //For Profile
//        viewHolder.profileName.setText(dataSet.get(position).getPlayerName());
//        viewHolder.profileCountryName.setText(dataSet.get(position).getCountry());
//        viewHolder.txtProfileAge.setText(dataSet.get(position).getCurrentAge());
//        viewHolder.txtProfileBorn.setText(dataSet.get(position).getBorn());
//        viewHolder.txtPlayingRole.setText(dataSet.get(position).getPlayingRole());
//        viewHolder.txtMajorTeam.setText(dataSet.get(position).getMajorTeams());
//        viewHolder.txtBattingStyle.setText(dataSet.get(position).getBattingStyle());
//        viewHolder.txtBowlingStyle.setText(dataSet.get(position).getBowlingStyle());
//
//
//        //For Batting Statistic
//        viewHolder.txtMatches.setText(dataSet.get(position).getMatches());
//        viewHolder.txtInnings.setText(dataSet.get(position).getInnings());
//        viewHolder.txtRuns.setText(dataSet.get(position).getRunsBatting());
//        viewHolder.txtHS.setText(dataSet.get(position).getHS());
//        viewHolder.txtAve.setText(dataSet.get(position).getAve());
//        viewHolder.txtSR.setText(dataSet.get(position).getSR());
//        viewHolder.txt50.setText(dataSet.get(position).getHalfCentury());
//        viewHolder.txt100.setText(dataSet.get(position).getCentury());
//        viewHolder.txt4s.setText(dataSet.get(position).getFours());
//        viewHolder.txt6s.setText(dataSet.get(position).getSixes());
//
//        //For Bowling Statistic
//        viewHolder.txtMatchesBowling.setText(dataSet.get(position).getMatchesBowling());
//        viewHolder.txtInningsBowling.setText(dataSet.get(position).getInningsBowling());
//        viewHolder.txtBalls.setText(dataSet.get(position).getBalls());
//        viewHolder.txtWkts.setText(dataSet.get(position).getWkts());
//        viewHolder.txt4w.setText(dataSet.get(position).getFourWicketsHaul());
//        viewHolder.txt5w.setText(dataSet.get(position).getFiveWicketsHaul());
//        viewHolder.txtAveBowling.setText(dataSet.get(position).getAveBowling());
//        viewHolder.txtEcon.setText(dataSet.get(position).getEcon());
//
//
//        String image = playerDetails.getmImageUrl();
//        if (image !=null && !image.trim().equals("")) {
//            Picasso.get()
//                    .load(image).into(viewHolder.imageView);
//        }
//
//
//        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainActivityIntent = new Intent(context
//                        , PlayerStatisticActivity.class);
//                PlayerDetails playerDetails=dataSet.get(position);
//                mainActivityIntent.putExtra("Player Details",playerDetails);
//                context.startActivity(mainActivityIntent);
//            }
//        });
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return dataSet.size();
//    }
//
//
//}
