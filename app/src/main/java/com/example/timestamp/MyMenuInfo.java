package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyMenuInfo {

    @SerializedName("myNum")
    @Expose
    private int myNum;
    @SerializedName("myTitle")
    @Expose
    private String myTitle;
    @SerializedName("myTitleImage")
    @Expose
    private String myTitleImage;


    public MyMenuInfo(int myNum, String myTitle, String myTitleImage) {
        this.myNum = myNum;
        this.myTitle = myTitle;
        this.myTitleImage = myTitleImage;
    }

    public int getMyNum() {
        return myNum;
    }

    public void setMyNum(int myNum) {
        this.myNum = myNum;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public void setMyTitle(String myTitle) {
        this.myTitle = myTitle;
    }

    public String getMyTitleImage() {
        return myTitleImage;
    }

    public void setMyTitleImage(String myTitleImage) {
        this.myTitleImage = myTitleImage;
    }
}
