package com.example.timestamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.timestamp.ui.stamp.StampAdd_ShareActivity;

public class StampAddMenuImageFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stamp_add_menu_image, container, false);

        Log.d("아아","이미지 화면");

        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();

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
                ((StampAdd_ShareActivity)getActivity()).replaceFragment(new StampAddMenuTagFragment());
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
