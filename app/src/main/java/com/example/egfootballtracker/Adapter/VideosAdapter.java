package com.example.egfootballtracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egfootballtracker.Model.Item;
import com.example.egfootballtracker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosViewHolder> {

    private final List<Item> items;
    private int rowLayout;
    private Context context;

    public static class VideosViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        ImageView VideoPoster;

        public VideosViewHolder(View v) {

            super(v);

            title = v.findViewById(R.id.txtVideoTitle);
            description =v.findViewById(R.id.txtVideoDescription);
            VideoPoster =v.findViewById(R.id.imgVideoPoster);
        }
    }

    public VideosAdapter(List<Item> sportVideos, int rowLayout, Context context) {
        this.items = sportVideos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_layout
                , parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder
            , final int position) {

        String item= items.get(position).getSnippet().getTitle();
        holder.title.setText(items.get(position).getSnippet().getTitle());

        String item2= items.get(position).getSnippet().getDescription();
        holder.description.setText(items.get(position).getSnippet().getDescription());

        String image_url =items.get(position).getSnippet().getThumbnails().getMedium().getUrl();
        Picasso.get().load(image_url).into(holder.VideoPoster);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
