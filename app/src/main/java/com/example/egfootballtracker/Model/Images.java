package com.example.egfootballtracker.Model;

import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("url")
    public String url;

    @SerializedName("width")
    public String width;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
