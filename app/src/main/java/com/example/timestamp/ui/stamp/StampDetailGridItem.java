package com.example.timestamp.ui.stamp;

public class StampDetailGridItem {

    String image;
    String userID;
    String userName;

    public StampDetailGridItem(String image, String userID, String userName) {
        this.image = image;
        this.userID = userID;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
