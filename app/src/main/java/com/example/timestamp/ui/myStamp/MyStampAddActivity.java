package com.example.timestamp.ui.myStamp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timestamp.R;
import com.example.timestamp.StampTitleImageActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class MyStampAddActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    int resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stamp_add);

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);


        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button titleButton = (Button) findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StampTitleImageActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Button titleButton_g = (Button) findViewById(R.id.titleButton_g);
        titleButton_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/-");
                startActivityForResult(intent, 1);
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("resId", resId);
                intent.putExtra("title", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bitmap bm;
        if (requestCode == 0) {
            if (intent != null) {
                resId = intent.getIntExtra("resId", 0);
                imageView.setImageResource(resId);
            }
        } else if (requestCode == 1) {
            if (intent != null) {
                try {
                    bm = MediaStore.Images.Media.getBitmap(getContentResolver(), intent.getData());
                    Bitmap bitmap = rotateImage(intent.getData(), bm);

                    if (bitmap.getHeight() >= bitmap.getWidth()) {
                        Bitmap bitmap1 = resizeBitmap(bitmap, bitmap.getWidth(), bitmap.getWidth());
                        Bitmap bitmap2 = cropBitmap(bitmap1, bitmap1.getWidth(), bitmap1.getWidth());
                        imageView.setImageBitmap(bitmap2);

                    } else {
                        Bitmap bitmap1 = resizeBitmap(bitmap, bitmap.getHeight(), bitmap.getHeight());
                        Bitmap bitmap2 = cropBitmap(bitmap1, bitmap1.getHeight(), bitmap1.getHeight());
                        imageView.setImageBitmap(bitmap2);


                    }


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (OutOfMemoryError e) {
                    Toast.makeText(getApplicationContext(), "이미지 용량이 너무 큽니다.", Toast.LENGTH_SHORT).show();
                }


            }

        }

    }


    public Bitmap rotateImage(Uri uri, Bitmap bitmap) throws IOException {
        InputStream in = getContentResolver().openInputStream(uri);
        ExifInterface exifInterface = new ExifInterface(in);
        in.close();

        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        Matrix matrix = new Matrix();
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            matrix.postRotate(90);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            matrix.postRotate(180);
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            matrix.postRotate(270);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Bitmap cropBitmap(Bitmap bitmap, int width, int height) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        // 이미지를 crop 할 좌상단 좌표
        int x = 0;
        int y = 0;

        if (originWidth > width) { // 이미지의 가로가 view 의 가로보다 크면..
            x = (originWidth - width) / 2;
        }

        if (originHeight > height) { // 이미지의 세로가 view 의 세로보다 크면..
            y = (originHeight - height) / 2;
        }

        Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
        return cropedBitmap;
    }//이미지 자르기

    public Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap.getWidth() != width || bitmap.getHeight() != height) {
            float ratio = 1.0f;

            if (width > height) {
                ratio = (float) width / (float) bitmap.getWidth();
            } else {
                ratio = (float) height / (float) bitmap.getHeight();
            }

            bitmap = Bitmap.createScaledBitmap(bitmap,
                    (int) (((float) bitmap.getWidth()) * ratio), // Width
                    (int) (((float) bitmap.getHeight()) * ratio), // Height
                    false);
        }

        return bitmap;
    }//이미지 사이즈 조절

//    private Bitmap resize(Bitmap bm) {
//        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 800)
//            bm = Bitmap.createScaledBitmap(bm, 400, 400, true);
//        else if (config.smallestScreenWidthDp >= 600)
//            bm = Bitmap.createScaledBitmap(bm, 300, 300, true);
//        else if (config.smallestScreenWidthDp >= 400)
//            bm = Bitmap.createScaledBitmap(bm, 200, 200, true);
//        else if (config.smallestScreenWidthDp >= 360)
//            bm = Bitmap.createScaledBitmap(bm, 180, 180, true);
//        else
//            bm = Bitmap.createScaledBitmap(bm, 160, 160, true);
//        return bm;
//    }


}
