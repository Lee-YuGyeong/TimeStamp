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

    boolean button1_vis = false;
    boolean button2_vis = false;
    boolean button3_vis = false;
    boolean button4_vis = false;
    boolean button5_vis = false;
    boolean button6_vis = false;
    boolean button7_vis = false;
    boolean button8_vis = false;
    boolean button9_vis = false;
    boolean button10_vis = false;
    boolean button11_vis = false;
    boolean button12_vis = false;
    boolean button13_vis = false;
    boolean button14_vis = false;


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

        callBack.TimeColorButtonSelected("color", color1, key, button1_vis);
        colorInit();
        buttonSelect(button1, color1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean vis = !button1_vis;
                button1_vis = !button1_vis;
                visible(button1, color1, button1_vis);
                button1_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color1, key, button1_vis);
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean vis = !button2_vis;
                button2_vis = !button2_vis;
                visible(button2, color2, button2_vis);
                button2_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color2, key, button2_vis);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean vis = !button3_vis;
                button3_vis = !button3_vis;
                visible(button3, color3, button3_vis);
                button3_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color3, key, button3_vis);
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button4_vis;
                button4_vis = !button4_vis;
                visible(button4, color4, button4_vis);
                button4_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color4, key, button4_vis);
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button5_vis;
                button5_vis = !button5_vis;
                visible(button5, color5, button5_vis);
                button5_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color5, key, button5_vis);
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button6_vis;
                button6_vis = !button6_vis;
                visible(button6, color6, button6_vis);
                button6_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color6, key, button6_vis);
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button7_vis;
                button7_vis = !button7_vis;
                visible(button7, color7, button7_vis);
                button7_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color7, key, button7_vis);
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button8_vis;
                button8_vis = !button8_vis;
                visible(button8, color8, button8_vis);
                button8_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color8, key, button8_vis);
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button9_vis;
                button9_vis = !button9_vis;
                visible(button9, color9, button9_vis);
                button9_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color9, key, button9_vis);
                }
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button10_vis;
                button10_vis = !button10_vis;
                visible(button10, color10, button10_vis);
                button10_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color10, key, button10_vis);
                }
            }
        });


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button11_vis;
                button11_vis = !button11_vis;
                visible(button11, color11, button11_vis);
                button11_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color11, key, button11_vis);
                }
            }
        });


        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button12_vis;
                button12_vis = !button12_vis;
                visible(button12, color12, button12_vis);
                button12_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color12, key, button12_vis);
                }
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button13_vis;
                button13_vis = !button13_vis;
                visible(button13, color13, button13_vis);
                button13_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color13, key, button13_vis);
                }
            }
        });


        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean vis = !button14_vis;
                button14_vis = !button14_vis;
                visible(button14, color14, button14_vis);
                button14_vis = vis;

                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", color14, key, button14_vis);
                }
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

    public void visible(Button button, int color, boolean vis) {

        if (vis) {
            initFalse();
            colorInit();
            buttonSelect(button, color);
        } else {
            colorInit();
        }
    }

    public void initFalse() {

        button1_vis = false;
        button2_vis = false;
        button3_vis = false;
        button4_vis = false;
        button5_vis = false;
        button6_vis = false;
        button7_vis = false;
        button8_vis = false;
        button9_vis = false;
        button10_vis = false;
        button11_vis = false;
        button12_vis = false;
        button13_vis = false;
        button14_vis = false;

    }


}
