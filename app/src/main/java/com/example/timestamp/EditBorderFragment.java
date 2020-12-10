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

public class EditBorderFragment extends Fragment {

    BorderSizeButtonFragment borderSizeButtonFragment;
    TimeColorButtonFragment timeColorButtonFragment;

    public FragmentManager fragmentManager;
    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_edit_border, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();


        if (borderSizeButtonFragment == null) {
            borderSizeButtonFragment = new BorderSizeButtonFragment();
            fragmentManager.beginTransaction().add(R.id.border_container, borderSizeButtonFragment).commit();
        }

        if (borderSizeButtonFragment != null) {
            fragmentManager.beginTransaction().show(borderSizeButtonFragment).commit();
        }
        if (timeColorButtonFragment != null) {
            fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
        }

        Button borderSizeButton = (Button) root.findViewById(R.id.borderSizeButton);
        borderSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (borderSizeButtonFragment == null) {
                    borderSizeButtonFragment = new BorderSizeButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.border_container, borderSizeButtonFragment).commit();
                }

                if (borderSizeButtonFragment != null) {
                    fragmentManager.beginTransaction().show(borderSizeButtonFragment).commit();
                }
                if (timeColorButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
                }

            }
        });

        Button timeColorButton = (Button) root.findViewById(R.id.borderColorButton);
        timeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeColorButtonFragment == null) {
                    timeColorButtonFragment = new TimeColorButtonFragment();
                    fragmentManager.beginTransaction().add(R.id.border_container, timeColorButtonFragment).commit();
                }

                if (borderSizeButtonFragment != null) {
                    fragmentManager.beginTransaction().hide(borderSizeButtonFragment).commit();
                }
                if (timeColorButtonFragment != null) {
                    fragmentManager.beginTransaction().show(timeColorButtonFragment).commit();
                }
                Bundle bundle = new Bundle();
                bundle.putString("key", "border");
                timeColorButtonFragment.setArguments(bundle);

            }
        });


        return root;

    }
}
