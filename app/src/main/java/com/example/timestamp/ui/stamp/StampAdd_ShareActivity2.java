package com.example.timestamp.ui.stamp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.R;
import com.example.timestamp.StampTitleImageActivity;
import com.example.timestamp.SuccessResponseInfo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StampAdd_ShareActivity2 extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    int resId;
    String title;


    String userID;

    Uri selectedImage;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_add_share);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("메뉴 추가");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);


        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");


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
                uploadMenu(selectedImage, userID, title, 1);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    public void uploadMenu(Uri fileUri, String userID, String drawerTitle, int share) {


        File file = new File(getRealPathFromURI(fileUri)); //절대경로로 바꾸기

        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody drawerTitleBody = RequestBody.create(MediaType.parse("text/plain"), drawerTitle);


        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.MenuUpload(requestFile, userIDBody, drawerTitleBody, share);
        //finally performing the call
        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, Response<SuccessResponseInfo> response) {
                if (response.isSuccessful()) {
                    setResult(RESULT_OK);
                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {

            }
        });

    }



    private String getRealPathFromURI(Uri contentURI) {

        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();

        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;

    } // 절대경로로 변환


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bitmap bm;
        if (requestCode == 0) { //제공된 사진 선택
            if (intent != null) {
                resId = intent.getIntExtra("resId", 0);
                imageView.setImageResource(resId);
            }
        } else if (requestCode == 1) { //갤러리 사진 선택
            if (intent != null) {
                //              try {

                selectedImage = intent.getData();
                imageView.setImageURI(selectedImage);

            }

        }

    }


}
