package com.example.timestamp.ui.stamp;

public class StampDetailGridItem {

    String image;
    String userID;

    public StampDetailGridItem(String image, String userID) {
        this.image = image;
        this.userID = userID;
    }

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
}
