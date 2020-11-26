package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.timestamp.ui.myStamp.MyStampDetailActivity;
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
        final LinearLayout container = (LinearLayout) findViewById(R.id.capture_target_Layout);

        byte[] arr = getIntent().getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);

        goToFragment(bitmap, fragmentImageEdit1, 0);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap captureBitmap = setViewToBitmapImage(container);
                ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                imageView2.setImageBitmap(captureBitmap);

                Intent intent = new Intent(getApplicationContext(), MyStampDetailActivity.class);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                captureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
//                intent.putExtra("captureBitmap",byteArray);
                startActivity(intent); //비트맵 서버 저장하기

            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    public static Bitmap setViewToBitmapImage(View view) {
//Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public void goToFragment(Bitmap bitmap, Fragment fragment, int i) {

        Bundle bundle = new Bundle();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bundle.putByteArray("bitmap", byteArray);
        fragment.setArguments(bundle);

        if (i == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }


    }


}
