package com.example.timestamp.ui.myStamp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.timestamp.R;

public class MyStampDetailGridItemView extends LinearLayout {

    ImageView imageView;

    public MyStampDetailGridItemView(Context context) {
        super(context);
        init(context);
    }

    public MyStampDetailGridItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_stamp_detail_grid_item, this, true);

        imageView = (ImageView) findViewById(R.id.imageView);
    }


    public void setImage(Bitmap resId) {
        imageView.setImageBitmap(resId);
    }

}
