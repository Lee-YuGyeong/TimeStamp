package com.example.timestamp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditTimeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_edit_time, container, false);


        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new TimeStyleButtonFragment()).commit();

        Button timeStyleButton = (Button) root.findViewById(R.id.timeStyleButton);
        timeStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TimeStyleButtonFragment()).commit();
            }
        });

        Button timeColorButton = (Button) root.findViewById(R.id.timeColorButton);
        timeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TimeColorButtonFragment()).commit();
            }
        });

        Button timeFontButton = (Button) root.findViewById(R.id.timeFontButton);
        timeFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TimeFontButtonFragment()).commit();
            }
        });


        return root;

    }

}
