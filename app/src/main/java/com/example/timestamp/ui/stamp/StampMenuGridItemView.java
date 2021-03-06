package com.example.timestamp.ui.stamp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.timestamp.R;

public class StampMenuGridItemView extends LinearLayout {

    ImageView imageView;
    TextView textView;
    ImageView people;

    public StampMenuGridItemView(Context context) {
        super(context);
        init(context);
    }

    public StampMenuGridItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stamp_menu_grid_item, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        people = (ImageView) findViewById(R.id.imageView_people);
    }

    public void setTitle(String title) {
        textView.setText(title);
    }

    public void setImage(String image) {
        Glide.with(getContext().getApplicationContext()).load(image).into(imageView);
    }

    public void setPeople(int share) {
        if (share == 0) {
            people.setVisibility(INVISIBLE);
        } else {
            people.setVisibility(VISIBLE);
        }
    }

    public void setColor(int color){
        textView.setTextColor(color);
    }


}
