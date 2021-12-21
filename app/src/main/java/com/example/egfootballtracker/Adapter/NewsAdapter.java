package com.example.egfootballtracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.egfootballtracker.Model.SportNews;
import com.example.egfootballtracker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private final List<com.example.egfootballtracker.Model.SportNews> SportNews;
    private int rowLayout;
    private Context context;




    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView author,title,description,moreInfo;
        ImageView newsPoster;

        public NewsViewHolder(View v) {

            super(v);

            author = v.findViewById(R.id.txtAuthor);
            title =v.findViewById(R.id.txtTitle);
            description=v.findViewById(R.id.txtDescription);
            newsPoster=v.findViewById(R.id.imgPoster);
            moreInfo=v.findViewById(R.id.btnMoreInfo);

        }
    }

    public NewsAdapter(List<SportNews> SportNews, int rowLayout, Context context) {
        this.SportNews = SportNews;
        this.rowLayout = rowLayout;
        this.context = context;



    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, parent,
                false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {

        String item= SportNews.get(position).getAuthor();
        holder.author.setText(SportNews.get(position).getAuthor());

        String item2=SportNews.get(position).getTitle();
        holder.title.setText(SportNews.get(position).getTitle());

        String item3=SportNews.get(position).getDescription();
        holder.description.setText(SportNews.get(position).getDescription());

        String item4=SportNews.get(position).getMoreInfo();
        holder.moreInfo.setText(SportNews.get(position).getMoreInfo());



        String image_url = SportNews.get(position).getImage();
        Picasso.get().load(image_url).into(holder.newsPoster);

    }

    @Override
    public int getItemCount() {
        return SportNews.size();
    }

}
