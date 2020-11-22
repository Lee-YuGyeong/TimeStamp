package com.example.timestamp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class StampTitleImageItemView extends LinearLayout {

    Button button;
    ImageView imageView;

    public StampTitleImageItemView(Context context) {
        super(context);

        init(context);
    }

    public StampTitleImageItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stamp_title_image_item,this,true);

   //     button = (Button) findViewById(R.id.imageButton);
        imageView = (ImageView) findViewById(R.id.imageView);

    }


    public void setImage(int resId) {
        //button.setBackgroundResource(resId);
        imageView.setBackgroundResource(resId);
    }
}
