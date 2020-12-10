package com.example.timestamp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditTimeFragment extends Fragment {

    Button timeStyleButton;
    Button timeColorButton;
    Button timeFontButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_edit_time, container, false);

        timeStyleButton = (Button) root.findViewById(R.id.timeStyleButton);
        timeColorButton = (Button) root.findViewById(R.id.timeColorButton);
        timeFontButton = (Button) root.findViewById(R.id.timeFontButton);

        timeStyleButton.setBackgroundResource(R.drawable.text_click);
        timeColorButton.setBackgroundResource(R.drawable.paint);
        timeFontButton.setBackgroundResource(R.drawable.font);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new TimeStyleButtonFragment()).commit();

        final TimeColorButtonFragment timeColorButtonFragment = new TimeColorButtonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "0");
        timeColorButtonFragment.setArguments(bundle);

        timeStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TimeStyleButtonFragment()).commit();

                timeStyleButton.setBackgroundResource(R.drawable.text_click);
                timeColorButton.setBackgroundResource(R.drawable.paint);
                timeFontButton.setBackgroundResource(R.drawable.font);

            }
        });


        timeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimeColorButtonFragment timeColorButtonFragment = new TimeColorButtonFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key", "time");
                timeColorButtonFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, timeColorButtonFragment).commit();

                timeStyleButton.setBackgroundResource(R.drawable.text);
                timeColorButton.setBackgroundResource(R.drawable.paint_click);
                timeFontButton.setBackgroundResource(R.drawable.font);
            }
        });


        timeFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TimeFontButtonFragment()).commit();

                timeStyleButton.setBackgroundResource(R.drawable.text);
                timeColorButton.setBackgroundResource(R.drawable.paint);
                timeFontButton.setBackgroundResource(R.drawable.font_click);
            }
        });


        return root;

    }


}
