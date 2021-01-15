package com.example.timestamp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timestamp.ui.stamp.StampAdd_ShareActivity;

public class StampAddMenuTitleFragment extends Fragment {

    String title;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stamp_add_menu_title, container, false);

        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();

        EditText editText = (EditText) root.findViewById(R.id.editText);
        title = editText.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.

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
                ((StampAdd_ShareActivity) getActivity()).replaceFragment(new StampAddMenuImageFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
