package com.example.egfootballtracker.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.example.egfootballtracker.Model.Matches;
import com.example.egfootballtracker.R;
import com.example.egfootballtracker.View.ShowMatchesActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiveScoreMatchesAdapter extends RecyclerView.Adapter<LiveScoreMatchesAdapter.ViewHolder> {
    private List<Matches> dataSet;
    private Context context;
    DatabaseReference selectedMatchDbRef;
    FirebaseDatabase database;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView teamNameA, teamNameB, teamNameAScores, teamNameBScores, matchTime;
        private LinearLayout layout;
        Button btnEdit;

        public ViewHolder(View view) {
            super(view);
            teamNameA = view.findViewById(R.id.txtTeamAScoreInput);
            teamNameB = view.findViewById(R.id.txtTeamBScoreInput);
            teamNameAScores = view.findViewById(R.id.txtTeamAScoreOutput);
            teamNameBScores = view.findViewById(R.id.txtTeamBScoreOuput);
            matchTime = view.findViewById(R.id.txtTimeOutput);
            layout = view.findViewById(R.id.matchesLayout);
            btnEdit = view.findViewById(R.id.btnEditMatch);
        }
    }

    public LiveScoreMatchesAdapter(Context context, List<Matches> dataSet) {
        this.dataSet = dataSet;
        this.context = context;
        this.database = FirebaseDatabase.getInstance();;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.matches_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        Matches selectedMatch = dataSet.get(position);
        selectedMatchDbRef = database.getReference("Match Scores/"+selectedMatch.getId());//Database name

        viewHolder.teamNameA.setText(selectedMatch.getTeamNameA());
        viewHolder.teamNameB.setText(selectedMatch.getTeamNameB());
        viewHolder.teamNameAScores.setText(selectedMatch.getTeamNameAScores());
        viewHolder.teamNameBScores.setText(selectedMatch.getTeamNameBScores());
        viewHolder.matchTime.setText(selectedMatch.getMatchTime());

        viewHolder.btnEdit.setOnClickListener(v -> {
            DialogPlus dialogPlus = DialogPlus.newDialog(context)
                    .setGravity(Gravity.CENTER)
                    .setMargin(50, 0, 50, 0)
                    .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_match_scores))
                    .setExpanded(false)
                    .create();

            View holderView = (LinearLayout) dialogPlus.getHolderView();

            EditText teamHomeScore = holderView.findViewById(R.id.txtUpdateHomeTeamScore);
            EditText teamAwayScore = holderView.findViewById(R.id.txtUpdateHostTeamScore);
            EditText matchTime = holderView.findViewById(R.id.txtUpdateMatchTime);

            teamHomeScore.setText(selectedMatch.getTeamNameAScores());
            teamAwayScore.setText(selectedMatch.getTeamNameBScores());
            matchTime.setText(selectedMatch.getMatchTime());

            Button update = holderView.findViewById(R.id.btnUpdateMatch);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedMatch.setTeamNameAScores(String.valueOf(teamHomeScore.getText()));
                    selectedMatch.setTeamNameB(String.valueOf(teamAwayScore.getText()));
                    selectedMatchDbRef.setValue(selectedMatch);
                    dialogPlus.dismiss();
                }
            });
            dialogPlus.show();
        });


    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
