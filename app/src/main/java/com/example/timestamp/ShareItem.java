package com.example.timestamp;

public class ShareItem {

    private String title;
    private String titleImage;
    private int people;

    public ShareItem(String title, String titleImage, int people) {
        this.title = title;
        this.titleImage = titleImage;
        this.people = people;
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
}