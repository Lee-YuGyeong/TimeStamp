package com.example.timestamp.ui.myStamp;

import android.graphics.Bitmap;
import android.widget.TextView;

public class MyStampDetailGridItem {

    Bitmap image;
    String date;

    public MyStampDetailGridItem(Bitmap image, String date) {
        this.image = image;
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
