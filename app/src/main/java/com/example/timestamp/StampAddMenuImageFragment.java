package com.example.timestamp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timestamp.ui.stamp.StampAddActivity;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;


import static android.content.Context.MODE_PRIVATE;


public class StampAddMenuImageFragment extends Fragment {

    Uri selectedImage;
    ImageView imageView;

    TextView textView_title;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stamp_add_menu_image, container, false);

        imageView = (ImageView) root.findViewById(R.id.imageView);
        textView_title = (TextView) root.findViewById(R.id.textView_title);
        textView_title.setText(((StampAddActivity) getActivity()).setTitleData());

        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/-");
                startActivityForResult(intent, 1);
            }
        });


        ColorPickerView colorPickerView = (ColorPickerView) root.findViewById(R.id.colorPickerView);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                textView_title.setTextColor(color);
                ((StampAddActivity) getActivity()).getColorData(color);
            }
        });


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
                if (selectedImage == null) {
                    Toast.makeText(getContext(), "이미지를 선택하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("mine", MODE_PRIVATE);
                int share = sharedPreferences.getInt("share", -1);

                if (share == 0) {
                    ((StampAddActivity) getActivity()).uploadMenu_my();
                } else if (share == 1) {
                    ((StampAddActivity) getActivity()).replaceFragment(new StampAddMenuTagFragment());
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bitmap bm;
        if (requestCode == 0) { //제공된 사진 선택
            if (intent != null) {
//                resId = intent.getIntExtra("resId", 0);
//                imageView.setImageResource(resId);
            }
        } else if (requestCode == 1) { //갤러리 사진 선택
            if (intent != null) {
                selectedImage = intent.getData();
                imageView.setImageURI(selectedImage);
                ((StampAddActivity) getActivity()).getImageData(selectedImage);
            }

        }

    }
}
