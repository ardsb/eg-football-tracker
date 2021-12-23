package com.example.egfootballtracker.Model;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {
    @SerializedName("medium")
    public Images medium;

    public Images getMedium() {
        return medium;
    }

    public void setMedium(String Images) {
        this.medium = medium;
    }
}
