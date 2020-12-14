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

public class TimeColorButtonFragment extends Fragment {

    FragmentCallBack callBack;

    String key;

    boolean vis = false;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;

    int color1 = Color.parseColor("#000000");
    int color2 = Color.parseColor("#FFFFFF");
    int color3 = Color.parseColor("#FF0000");
    int color4 = Color.parseColor("#FF5E00");
    int color5 = Color.parseColor("#FFBB00");
    int color6 = Color.parseColor("#FFE400");
    int color7 = Color.parseColor("#ABF200");
    int color8 = Color.parseColor("#FF007F");
    int color9 = Color.parseColor("#FF00DD");
    int color10 = Color.parseColor("#6600FF");
    int color11 = Color.parseColor("#0900FF");
    int color12 = Color.parseColor("#0055FF");
    int color13 = Color.parseColor("#00D8FF");
    int color14 = Color.parseColor("#1FDA11");

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

        View root = inflater.inflate(R.layout.fragment_color_button, container, false);

        key = getArguments().getString("key");


        button1 = (Button) root.findViewById(R.id.button1);
        button2 = (Button) root.findViewById(R.id.button2);
        button3 = (Button) root.findViewById(R.id.button3);
        button4 = (Button) root.findViewById(R.id.button4);
        button5 = (Button) root.findViewById(R.id.button5);
        button6 = (Button) root.findViewById(R.id.button6);
        button7 = (Button) root.findViewById(R.id.button7);
        button8 = (Button) root.findViewById(R.id.button8);
        button9 = (Button) root.findViewById(R.id.button9);
        button10 = (Button) root.findViewById(R.id.button10);
        button11 = (Button) root.findViewById(R.id.button11);
        button12 = (Button) root.findViewById(R.id.button12);
        button13 = (Button) root.findViewById(R.id.button13);
        button14 = (Button) root.findViewById(R.id.button14);

        callBack.TimeColorButtonSelected("color", color1, key);
        colorInit();
        buttonSelect(button1, color1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color1, key);
                }
                colorInit();
                buttonSelect(button1, color1);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color2, key);
                }
                colorInit();
                buttonSelect(button2, color2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color3, key);
                }
                colorInit();
                buttonSelect(button3, color3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color4, key);
                }
                colorInit();
                buttonSelect(button4, color4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color5, key);
                }
                colorInit();
                buttonSelect(button5, color5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color6, key);
                }
                colorInit();
                buttonSelect(button6, color6);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color7, key);
                }
                colorInit();
                buttonSelect(button7, color7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color8, key);
                }
                colorInit();
                buttonSelect(button8, color8);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color9, key);
                }
                colorInit();
                buttonSelect(button9, color9);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color10, key);
                }
                colorInit();
                buttonSelect(button10, color10);
            }
        });


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color11, key);
                }
                colorInit();
                buttonSelect(button11, color11);
            }
        });


        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color12, key);
                }
                colorInit();
                buttonSelect(button12, color12);
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color13, key);
                }
                colorInit();
                buttonSelect(button13, color13);
            }
        });


        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color14, key);
                }
                colorInit();
                buttonSelect(button14, color14);
            }
        });


        return root;
    }


    public void colorInit() {

        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable.setColor(color1);
        drawable.setStroke(0, Color.WHITE);
        button1.setBackground(drawable);

        GradientDrawable drawable2 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable2.setColor(color2);
        drawable2.setStroke(0, Color.WHITE);
        button2.setBackground(drawable2);

        GradientDrawable drawable3 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable3.setColor(color3);
        drawable3.setStroke(0, Color.WHITE);
        button3.setBackground(drawable3);

        GradientDrawable drawable4 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable4.setColor(color4);
        drawable4.setStroke(0, Color.WHITE);
        button4.setBackground(drawable4);

        GradientDrawable drawable5 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable5.setColor(color5);
        drawable5.setStroke(0, Color.WHITE);
        button5.setBackground(drawable5);

        GradientDrawable drawable6 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable6.setColor(color6);
        drawable6.setStroke(0, Color.WHITE);
        button6.setBackground(drawable6);

        GradientDrawable drawable7 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable7.setColor(color7);
        drawable7.setStroke(0, Color.WHITE);
        button7.setBackground(drawable7);

        GradientDrawable drawable8 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable8.setColor(color8);
        drawable8.setStroke(0, Color.WHITE);
        button8.setBackground(drawable8);

        GradientDrawable drawable9 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable9.setColor(color9);
        drawable9.setStroke(0, Color.WHITE);
        button9.setBackground(drawable9);

        GradientDrawable drawable10 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable10.setColor(color10);
        drawable10.setStroke(0, Color.WHITE);
        button10.setBackground(drawable10);

        GradientDrawable drawable11 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable11.setColor(color11);
        drawable11.setStroke(0, Color.WHITE);
        button11.setBackground(drawable11);

        GradientDrawable drawable12 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable12.setColor(color12);
        drawable12.setStroke(0, Color.WHITE);
        button12.setBackground(drawable12);

        GradientDrawable drawable13 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable13.setColor(color13);
        drawable13.setStroke(0, Color.WHITE);
        button13.setBackground(drawable13);

        GradientDrawable drawable14 = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable14.setColor(color14);
        drawable14.setStroke(0, Color.WHITE);
        button14.setBackground(drawable14);

    }

    public void buttonSelect(Button button, int color) {

        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(getActivity(), R.drawable.color_button_border);
        drawable.setStroke(10, Color.BLACK);
        drawable.setColor(color);
        button.setBackground(drawable);

    }


}
