package com.example.egfootballtracker.Model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("snippet")
    public com.example.egfootballtracker.Model.Snippet snippet;

    public com.example.egfootballtracker.Model.Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(com.example.egfootballtracker.Model.Snippet snippet) {
        this.snippet = snippet;
    }
}
