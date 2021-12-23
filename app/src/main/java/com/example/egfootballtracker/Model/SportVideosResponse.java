package com.example.egfootballtracker.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SportVideosResponse {

    @SerializedName("items")
    public List<Item> item;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}

