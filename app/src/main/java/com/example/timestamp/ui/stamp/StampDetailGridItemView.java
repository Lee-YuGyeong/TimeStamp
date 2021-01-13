package com.example.timestamp.ui.stamp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.timestamp.R;

public class StampDetailGridItemView extends LinearLayout {

    ImageView imageView;

    public StampDetailGridItemView(Context context) {
        super(context);
        init(context);
    }

    public StampDetailGridItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_stamp_detail_grid_item, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
    }


    public void setImage(String image) {
        Glide.with(getContext().getApplicationContext()).load(image).into(imageView);
    }


}
