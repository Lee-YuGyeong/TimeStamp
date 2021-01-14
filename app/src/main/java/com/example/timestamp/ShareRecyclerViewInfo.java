package com.example.timestamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareRecyclerViewInfo {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleImage")
    @Expose
    private String titleImage;
    @SerializedName("people")
    @Expose
    private int people;

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
}
