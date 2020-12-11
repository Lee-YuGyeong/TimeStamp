package com.example.timestamp;

import android.content.Context;
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

    TimeColorButtonFragment timeColorButtonFragment;

    public FragmentManager fragmentManager;

    boolean vis = false;

    FragmentCallBack callBack;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_edit_border, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();


        final Button timeColorButton = (Button) root.findViewById(R.id.borderColorButton);
        timeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vis == false) {
                    vis = true;
                    timeColorButton.setText("있음");
                } else {
                    vis = false;
                    timeColorButton.setText("없음");
                }

                if (vis == true) {

                    if (timeColorButtonFragment == null) {
                        timeColorButtonFragment = new TimeColorButtonFragment();
                        fragmentManager.beginTransaction().add(R.id.border_container, timeColorButtonFragment).commit();
                    }
                    if (timeColorButtonFragment != null) {
                        fragmentManager.beginTransaction().show(timeColorButtonFragment).commit();
                    }

                } else {
                    if (timeColorButtonFragment != null) {
                        fragmentManager.beginTransaction().hide(timeColorButtonFragment).commit();
                    }

                }

                callBack.BorderButtonSelected("테두리",vis);

                Bundle bundle = new Bundle();
                bundle.putString("key", "border");
                timeColorButtonFragment.setArguments(bundle);

            }
        });


        return root;

    }
}
