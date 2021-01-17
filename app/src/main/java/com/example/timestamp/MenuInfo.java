package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuInfo {

    @SerializedName("num")
    @Expose
    private int num;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleImage")
    @Expose
    private String titleImage;
    @SerializedName("share")
    @Expose
    private int share;
    @SerializedName("color")
    @Expose
    private int color;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
