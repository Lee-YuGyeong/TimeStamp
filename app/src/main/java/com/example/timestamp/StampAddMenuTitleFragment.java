package com.example.timestamp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.timestamp.ui.stamp.StampAddActivity;

public class StampAddMenuTitleFragment extends Fragment {

    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stamp_add_menu_title, container, false);

        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();

        editText = (EditText) root.findViewById(R.id.editText);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.stamp_add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nextButton:
                String Data = editText.getText().toString();
                if (Data.getBytes().length <= 0) {//빈값이 넘어올때의 처리
                    Toast.makeText(getContext(), "방 이름을 설정하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                ((StampAddActivity) getActivity()).getTitleData(Data);
                ((StampAddActivity) getActivity()).replaceFragment(new StampAddMenuImageFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
