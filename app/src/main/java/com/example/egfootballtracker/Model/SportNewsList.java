package com.example.egfootballtracker.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SportNewsList {

    @SerializedName("articles")
    public List<SportNews> article;

    public List<SportNews> getArticle() {
        return article;
    }

    public void setArticle(List<SportNews> article) {
        this.article = article;
    }
}
