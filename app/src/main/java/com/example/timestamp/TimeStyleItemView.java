package com.example.timestamp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TimeStyleItemView extends LinearLayout {

    TextView textView;

    public TimeStyleItemView(Context context) {
        super(context);
        init(context);
    }

    public TimeStyleItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stamp_time_style_item, this, true);

        textView = (TextView) findViewById(R.id.textView);

    }


    public void setText(String text) {
        textView.setText(text);
    }
}
