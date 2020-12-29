package com.example.timestamp.ui.myStamp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.timestamp.MyMenuInfo;
import com.example.timestamp.MyResponse;
import com.example.timestamp.R;
import com.example.timestamp.ResponseInfo;
import com.example.timestamp.RetrofitClient;
import com.example.timestamp.StampTitleImageActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyStampAddActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    int resId;
    String title;

    String userID;

    Bitmap selectedBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stamp_add);

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);


        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");


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

                title = editText.getText().toString();

               // Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.logo);
                BitmapSave(selectedBitmap, title); //이미지 서버 저장


                Intent intent = new Intent();
                intent.putExtra("resId", resId);
                intent.putExtra("title", title);
                setResult(RESULT_OK, intent);
                finish();


            }
        });
    }

    public void BitmapSave(Bitmap bitmap, String title) {

        File imageFile = null;
        try {
            imageFile = createFileFromBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            uploadMenu(imageFile, userID, title);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public File createFileFromBitmap(Bitmap bitmap) throws IOException {
        File newFile = new File(getFilesDir(), makeImageFileName());
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, fileOutputStream);
        fileOutputStream.close();
        return newFile;
    }//비트맵 -> 파일 변환


    public String makeImageFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        Date date = new Date();
        String strDate = simpleDateFormat.format(date);
        return strDate + ".png";
    }

    public void uploadMenu(File file, String userID, String drawerTitle) throws URISyntaxException {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody drawerTitleBody = RequestBody.create(MediaType.parse("text/plain"), drawerTitle);

        Call<List<MyResponse>> call = RetrofitClient.getInstance().getApi().uploadImage(requestFile, userIDBody, drawerTitleBody);
        //finally performing the call
        call.enqueue(new Callback<List<MyResponse>>() {
            @Override
            public void onResponse(Call<List<MyResponse>> call, Response<List<MyResponse>> response) {
            }

            @Override
            public void onFailure(Call<List<MyResponse>> call, Throwable t) {
            }
        });
    } //retrofit2 내 메뉴 업로드

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
                        selectedBitmap = bitmap2;
                        imageView.setImageBitmap(bitmap2);

                    } else {
                        Bitmap bitmap1 = resizeBitmap(bitmap, bitmap.getHeight(), bitmap.getHeight());
                        Bitmap bitmap2 = cropBitmap(bitmap1, bitmap1.getHeight(), bitmap1.getHeight());
                        selectedBitmap = bitmap2;
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


}
