package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuDetailInfo {

//    @SerializedName("myNum")
//    @Expose
//    private int myNum;
//    @SerializedName("image")
//    @Expose
//    private String image;
//
//
//    public MenuDetailInfo(int myNum, String image) {
//        this.myNum = myNum;
//        this.image = image;
//    }
//
//    public int getMyNum() {
//        return myNum;
//    }
//
//    public void setMyNum(int myNum) {
//        this.myNum = myNum;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

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
