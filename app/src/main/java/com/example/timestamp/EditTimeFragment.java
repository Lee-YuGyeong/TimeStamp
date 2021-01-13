package com.example.timestamp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class EditTimeFragment extends Fragment {

    Button timeStyleButton;
    Button timeColorButton;
    Button timeFontButton;

    TimeStyleButtonFragment timeStyleButtonFragment;
    TimeColorButtonFragment timeColorButtonFragment;
    TimeFontButtonFragment timeFontButtonFragment;

    public FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_edit_time, container, false);

        timeStyleButton = (Button) root.findViewById(R.id.timeStyleButton);
        timeColorButton = (Button) root.findViewById(R.id.timeColorButton);
        timeFontButton = (Button) root.findViewById(R.id.timeFontButton);

        timeStyleButton.setBackgroundResource(R.drawable.ic_text_fields_black_24dp);
        timeColorButton.setBackgroundResource(R.drawable.color_wheel);
        timeFontButton.setBackgroundResource(R.drawable.ic_font_download_gray_24dp);

        fragmentManager = getActivity().getSupportFragmentManager();


        if (timeStyleButtonFragment == null) {
            timeStyleButtonFragment = new TimeStyleButtonFragment();
            fragmentManager.beginTransaction().add(R.id.time_container, timeStyleButtonFragment).commit();
        }

        if (timeStyleButtonFragment != null) {
            fragmentManager.beginTransaction().show(timeStyleButtonFragment).commit();
        }
        if (timeColorButtonFragment != null) {
            fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
        }
        if (timeFontButtonFragment != null) {
            fragmentManager.beginTransaction().hide(timeFontButtonFragment).commit();
        }


        timeStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timeStyleButtonFragment == null) {
                    timeStyleButtonFragment = new TimeStyleButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.time_container, timeStyleButtonFragment).commit();
                }

                if (timeStyleButtonFragment != null) {
                    fragmentManager.beginTransaction().show(timeStyleButtonFragment).commit();
                }
                if (timeColorButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
                }
                if (timeFontButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeFontButtonFragment).commit();
                }


                timeStyleButton.setBackgroundResource(R.drawable.ic_text_fields_black_24dp);
                timeColorButton.setBackgroundResource(R.drawable.color_wheel);
                timeFontButton.setBackgroundResource(R.drawable.ic_font_download_gray_24dp);

            }
        });


        timeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (timeColorButtonFragment == null) {
                    timeColorButtonFragment = new TimeColorButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.time_container, timeColorButtonFragment).commit();
                }

                if (timeStyleButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeStyleButtonFragment).commit();
                }
                if (timeColorButtonFragment != null) {
                    fragmentManager.beginTransaction().show(timeColorButtonFragment).commit();
                }
                if (timeFontButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeFontButtonFragment).commit();
                }

                timeStyleButton.setBackgroundResource(R.drawable.ic_text_fields_gray_24dp);
                timeColorButton.setBackgroundResource(R.drawable.color_wheel_click);
                timeFontButton.setBackgroundResource(R.drawable.ic_font_download_gray_24dp);

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
                    fragmentManager.beginTransaction().add(R.id.time_container, timeFontButtonFragment).commit();
                }

                if (timeStyleButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeStyleButtonFragment).commit();
                }
                if (timeColorButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
                }
                if (timeFontButtonFragment != null) {
                    fragmentManager.beginTransaction().show(timeFontButtonFragment).commit();
                }
                timeStyleButton.setBackgroundResource(R.drawable.ic_text_fields_gray_24dp);
                timeColorButton.setBackgroundResource(R.drawable.color_wheel);
                timeFontButton.setBackgroundResource(R.drawable.ic_font_download_black_24dp);
            }
        });


        return root;

    }


}
