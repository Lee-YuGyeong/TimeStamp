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

    public MenuInfo(int num, String title, String titleImage, int share) {
        this.num = num;
        this.title = title;
        this.titleImage = titleImage;
        this.share = share;
    }

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
}
