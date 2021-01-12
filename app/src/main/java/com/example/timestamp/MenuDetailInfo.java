package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuDetailInfo {

    @SerializedName("image")
    @Expose
    private String image;

    public MenuDetailInfo(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
