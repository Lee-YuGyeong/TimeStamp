package com.example.timestamp;

import java.io.Serializable;

public class ShareItem implements Serializable {

    private int num;
    private String title;
    private String titleImage;
    private int people;
    private String tag;

    public ShareItem() {
    }

    public ShareItem(int num, String title, String titleImage, int people, String tag) {
        this.num = num;
        this.title = title;
        this.titleImage = titleImage;
        this.people = people;
        this.tag = tag;
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