package com.example.egfootballtracker.Model;

import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("thumbnails")
    public com.example.egfootballtracker.Model.Thumbnail thumbnails;

    public com.example.egfootballtracker.Model.Thumbnail getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String Thumbnail) {
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
