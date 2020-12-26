package com.example.timestamp.ui.myStamp;

import android.graphics.Bitmap;

public class MyStampMenuGridItem {

    String image;
    String title;

    public MyStampMenuGridItem(String image, String title) {
        this.image = image;
        this.title = title;
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
}
