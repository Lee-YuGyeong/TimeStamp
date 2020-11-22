package com.example.timestamp.ui.myStamp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.timestamp.R;

public class MyStampMenuGridItemView extends LinearLayout {

    Button button;
    TextView textView;

    public MyStampMenuGridItemView(Context context) {
        super(context);

        init(context);
    }

    public MyStampMenuGridItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_stamp_menu_grid_item,this,true);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void setTitle(String title) {
        textView.setText(title);
    }

    public void setImage(int resId) {
        button.setBackgroundResource(resId);
    }

}
