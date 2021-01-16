package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareRecyclerViewInfo {

    @SerializedName("num")
    @Expose
    private int num;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleImage")
    @Expose
    private String titleImage;
    @SerializedName("people")
    @Expose
    private int people;
    @SerializedName("tag")
    @Expose
    private String tag;

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

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
