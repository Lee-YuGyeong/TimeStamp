package com.example.timestamp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TimeColorButtonFragment extends Fragment {

    FragmentCallBack callBack;

    String key;

    boolean vis = false;

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


        final Button button1 = (Button) root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button1.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button2.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button3 = (Button) root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button3.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button4 = (Button) root.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button4.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button5 = (Button) root.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button5.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button6 = (Button) root.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button6.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button7 = (Button) root.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button7.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button8 = (Button) root.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button8.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button9 = (Button) root.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button9.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }
            }
        });

        final Button button10 = (Button) root.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button10.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }

            }
        });

        final Button button11 = (Button) root.findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button11.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }

            }
        });


        final Button button12 = (Button) root.findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button12.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }

            }
        });


        final Button button13 = (Button) root.findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button13.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }

            }
        });


        final Button button14 = (Button) root.findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    ColorDrawable buttonColor = (ColorDrawable) button14.getBackground();
                    int colorId = buttonColor.getColor();
                    callBack.TimeColorButtonSelected("color", colorId, key);
                }

            }
        });




        return root;
    }

    public boolean visibility(boolean vis) {

        if (vis == false) {
            return true;
        } else {
            return false;
        }

    }

}
