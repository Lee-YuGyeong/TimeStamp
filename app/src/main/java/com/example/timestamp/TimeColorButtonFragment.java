package com.example.timestamp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

        View root = inflater.inflate(R.layout.fragment_color_button, container, false);

        key = getArguments().getString("key");


        final Button button1 = (Button) root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFFFFF", key);
                }
            }
        });

        final Button button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FF0000", key);
                }
            }
        });

        final Button button3 = (Button) root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FF5E00", key);
                }
            }
        });

        final Button button4 = (Button) root.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFBB00", key);
                }
            }
        });

        final Button button5 = (Button) root.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFE400", key);
                }
            }
        });

        final Button button6 = (Button) root.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#1FDA11", key);
                }
            }
        });

        final Button button7 = (Button) root.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFE400", key);
                }
            }
        });

        final Button button8 = (Button) root.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFE400", key);
                }
            }
        });

        final Button button9 = (Button) root.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFE400", key);
                }
            }
        });

        final Button button10 = (Button) root.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeColorButtonSelected("color", "#FFE400", key);
                }

            }
        });


        return root;
    }


}
