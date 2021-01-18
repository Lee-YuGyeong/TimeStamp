package com.example.timestamp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.timestamp.ui.stamp.StampAddActivity;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

public class TimeColorButtonFragment extends Fragment {

    FragmentCallBack callBack;

    String key;

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        if (context instanceof FragmentCallBack) {
            callBack = (FragmentCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (callBack != null)
            callBack = null;

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_color_palette, container, false);

        key = getArguments().getString("key");

        Button button = (Button) root.findViewById(R.id.button);
        Button button_border = (Button) root.findViewById(R.id.button_border);

        if (key == "time") {
            button.setText("테두리와 같은 색");
        } else {
            button.setText("시간과 같은색 ");
            button_border.setVisibility(View.VISIBLE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.TimeColorButtonSelected("color", key);
            }
        });

        button_border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.BorderButtonSelected("color");
            }
        });

        callBack.TimeColorPaletteSelected("color", -1, key);

        ColorPickerView colorPickerView = (ColorPickerView) root.findViewById(R.id.colorPickerView);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                callBack.TimeColorPaletteSelected("color", color, key);
            }
        });


        return root;
    }


}
