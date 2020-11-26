package com.example.timestamp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentImageEdit1 extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_image_edit1, container, false);

        Bundle bundle = new Bundle();
        byte[] arr = bundle.getByteArray("bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);


        ImageView imageView = (ImageView) root.findViewById(R.id.imageView);

        imageView.setImageBitmap(bitmap);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) \na h:mm:ss");
        String getTime = simpleDate.format(mDate);

        TextView textView = (TextView) root.findViewById(R.id.textView);
        textView.setText(getTime);

        return root;
    }


}
