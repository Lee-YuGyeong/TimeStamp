package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.timestamp.ui.myStamp.MyStampDetailGridItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageEditActivity extends AppCompatActivity {

    FragmentImageEdit1 fragmentImageEdit1;
    FragmentImageEdit2 fragmentImageEdit2;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        fragmentImageEdit1 = new FragmentImageEdit1();
        fragmentImageEdit2 = new FragmentImageEdit2();

        byte[] arr = getIntent().getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);

        goToFragment(bitmap, fragmentImageEdit1, 0);


        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentImageEdit1 != null) {
                    goToFragment(bitmap, fragmentImageEdit1, 1);
                }
            }
        });


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentImageEdit2 != null) {
                    goToFragment(bitmap, fragmentImageEdit2, 1);
                }
            }
        });


    }

    public void goToFragment(Bitmap bitmap, Fragment fragment, int i) {

        Bundle bundle = new Bundle();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bundle.putByteArray("bitmap", byteArray);
        fragment.setArguments(bundle);

        if (i == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
        }


    }


}
