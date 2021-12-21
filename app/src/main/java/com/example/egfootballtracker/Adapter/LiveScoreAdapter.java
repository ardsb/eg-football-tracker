package com.example.egfootballtracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.egfootballtracker.Model.Matches;
import com.example.egfootballtracker.R;

import java.util.List;

public class LiveScoreAdapter extends RecyclerView.Adapter<LiveScoreAdapter.ViewHolder>{
    private List<Matches> dataSet;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder   {
        private TextView teamNameA, teamNameB,teamNameAScores,teamNameBScores;
        private LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            teamNameA = view.findViewById(R.id.txtTeamAScoreInput);
            teamNameB = view.findViewById(R.id.txtTeamBScoreInput);
            teamNameAScores = view.findViewById(R.id.txtTeamAScoreOutput);
            teamNameBScores=view.findViewById(R.id.txtTeamBScoreOuput);
            layout = view.findViewById(R.id.matchesLayout);

        }
    }

    public LiveScoreAdapter(Context context, List<Matches> dataSet) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.matches_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.teamNameA.setText(dataSet.get(position).getTeamNameA());
        viewHolder.teamNameB.setText(dataSet.get(position).getTeamNameB());
        viewHolder.teamNameAScores.setText(dataSet.get(position).getTeamNameAScores());
        viewHolder.teamNameBScores.setText(dataSet.get(position).getTeamNameBScores());

    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
