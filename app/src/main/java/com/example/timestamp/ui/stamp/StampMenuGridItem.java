package com.example.timestamp.ui.stamp;


public class StampMenuGridItem {

    String image;
    String title;
    int num;
    int share;

    public StampMenuGridItem(String image, String title, int num, int share) {
        this.image = image;
        this.title = title;
        this.num = num;
        this.share = share;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
