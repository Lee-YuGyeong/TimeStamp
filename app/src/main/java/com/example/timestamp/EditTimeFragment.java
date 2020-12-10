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
import androidx.fragment.app.FragmentManager;

import java.sql.Time;

public class EditTimeFragment extends Fragment {

    Button timeStyleButton;
    Button timeColorButton;
    Button timeFontButton;

    TimeStyleButtonFragment timeStyleButtonFragment;
    TimeColorButtonFragment timeColorButtonFragment;
    TimeFontButtonFragment timeFontButtonFragment;

    public FragmentManager fragmentManager;;

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

        fragmentManager = getActivity().getSupportFragmentManager();

        timeStyleButtonFragment = new TimeStyleButtonFragment();
        fragmentManager.beginTransaction().add(R.id.container, timeStyleButtonFragment).commit();


        timeStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timeStyleButtonFragment == null) {
                    timeStyleButtonFragment = new TimeStyleButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.container, timeStyleButtonFragment).commit();
                }

                if (timeStyleButtonFragment != null) fragmentManager.beginTransaction().show(timeStyleButtonFragment).commit();
                if (timeColorButtonFragment != null) fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
                if (timeFontButtonFragment != null) fragmentManager.beginTransaction().hide(timeFontButtonFragment).commit();

                timeStyleButton.setBackgroundResource(R.drawable.text_click);
                timeColorButton.setBackgroundResource(R.drawable.paint);
                timeFontButton.setBackgroundResource(R.drawable.font);

            }
        });


        timeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timeColorButtonFragment == null) {
                    timeColorButtonFragment = new TimeColorButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.container, timeColorButtonFragment).commit();
                }

                if (timeStyleButtonFragment != null) fragmentManager.beginTransaction().hide(timeStyleButtonFragment).commit();
                if (timeColorButtonFragment != null) fragmentManager.beginTransaction().show(timeColorButtonFragment).commit();
                if (timeFontButtonFragment != null) fragmentManager.beginTransaction().hide(timeFontButtonFragment).commit();


                timeStyleButton.setBackgroundResource(R.drawable.text);
                timeColorButton.setBackgroundResource(R.drawable.paint_click);
                timeFontButton.setBackgroundResource(R.drawable.font);

                Bundle bundle = new Bundle();
                bundle.putString("key", "time");
                timeColorButtonFragment.setArguments(bundle);
            }
        });


        timeFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timeFontButtonFragment == null) {
                    timeFontButtonFragment = new TimeFontButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.container, timeFontButtonFragment).commit();
                }

                if (timeStyleButtonFragment != null) fragmentManager.beginTransaction().hide(timeStyleButtonFragment).commit();
                if (timeColorButtonFragment != null) fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
                if (timeFontButtonFragment != null) fragmentManager.beginTransaction().show(timeFontButtonFragment).commit();

                timeStyleButton.setBackgroundResource(R.drawable.text);
                timeColorButton.setBackgroundResource(R.drawable.paint);
                timeFontButton.setBackgroundResource(R.drawable.font_click);
            }
        });


        return root;

    }


}
