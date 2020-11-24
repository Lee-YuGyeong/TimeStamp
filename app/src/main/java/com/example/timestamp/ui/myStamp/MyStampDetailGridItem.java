package com.example.timestamp.ui.myStamp;

import android.graphics.Bitmap;

public class MyStampDetailGridItem {

    Bitmap image;

    public MyStampDetailGridItem(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
