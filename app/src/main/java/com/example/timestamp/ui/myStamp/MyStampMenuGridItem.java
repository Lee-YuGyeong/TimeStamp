package com.example.timestamp.ui.myStamp;

import android.graphics.Bitmap;

public class MyStampMenuGridItem {

    String image;
    String title;
    int num;

    public MyStampMenuGridItem(String image, String title, int num) {
        this.image = image;
        this.title = title;
        this.num = num;
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
}
