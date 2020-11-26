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


    final String TAG = getClass().getSimpleName();
    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    FragmentImageEdit1 fragmentImageEdit1;
    FragmentImageEdit2 fragmentImageEdit2;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        fragmentImageEdit1 = new FragmentImageEdit1();
        fragmentImageEdit2 = new FragmentImageEdit2();

//        NewThread thread = new NewThread();
//        thread.start();
        byte[] arr = getIntent().getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);

        imageView.setImageBitmap(bitmap);

        go(bitmap,fragmentImageEdit1);



        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentImageEdit1 != null) {

                    Bundle bundle = new Bundle();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    bundle.putByteArray("bitmap", byteArray);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentImageEdit1).addToBackStack(null).commit();

                }
            }
        });


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentImageEdit2 != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentImageEdit2).addToBackStack(null).commit();

                }
            }
        });


    }

    public void go(Bitmap bitmap, Fragment fragment){
        Bundle bundle = new Bundle();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bundle.putByteArray("bitmap", byteArray);
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();

    }

//    class NewThread extends Thread {
//
//        public NewThread() {
//        }
//
//        @Override
//        public void run() {
//            super.run();
//
//
//        }
//    }


}
