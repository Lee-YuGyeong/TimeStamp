package com.example.timestamp.ui.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.timestamp.R;

import org.w3c.dom.Text;

public class SettingFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        TextView textView = (TextView) root.findViewById(R.id.textView);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mine", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "null");

        textView.setText(userID);

        return root;
    }
}