package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageDetailInfo {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("userName")
    @Expose
    private String userName;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
