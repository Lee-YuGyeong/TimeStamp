package com.example.timestamp.ui.myStamp;

import android.graphics.Bitmap;

public class MyStampMenuGridItem {

    Bitmap bitmap;
    String title;

    public MyStampMenuGridItem(Bitmap bitmap, String title) {
        this.bitmap = bitmap;
        this.title = title;
    }

    public Bitmap getResId() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
