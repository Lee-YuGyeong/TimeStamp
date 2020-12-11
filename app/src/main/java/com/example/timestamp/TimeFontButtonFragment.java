package com.example.timestamp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TimeFontButtonFragment extends Fragment {

    FragmentCallBack callBack;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;


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

        View root = inflater.inflate(R.layout.fragment_font_button, container, false);

        button1 = (Button) root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeFontButtonSelected("font", 1);
                }
                buttonNotSelect();
                buttonSelect(button1);
            }
        });

        button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeFontButtonSelected("font", 2);
                }
                buttonNotSelect();
                buttonSelect(button2);
            }
        });

        button3 = (Button) root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeFontButtonSelected("font", 3);
                }
                buttonNotSelect();
                buttonSelect(button3);
            }
        });

        button4 = (Button) root.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.TimeFontButtonSelected("font", 4);
                }
                buttonNotSelect();
                buttonSelect(button4);
            }
        });

        button5 = (Button) root.findViewById(R.id.button5);
        button6 = (Button) root.findViewById(R.id.button6);

        return root;
    }


    public void buttonSelect(Button button) {
        button.setBackgroundResource(R.drawable.button_border_click);
    }

    public void buttonNotSelect() {
        button1.setBackgroundResource(R.drawable.button_border);
        button2.setBackgroundResource(R.drawable.button_border);
        button3.setBackgroundResource(R.drawable.button_border);
        button4.setBackgroundResource(R.drawable.button_border);
        button5.setBackgroundResource(R.drawable.button_border);
        button6.setBackgroundResource(R.drawable.button_border);
    }

}
