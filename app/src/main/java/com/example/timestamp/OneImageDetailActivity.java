package com.example.timestamp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.login.LoginActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OneImageDetailActivity extends AppCompatActivity {

    String url;

    Toolbar toolbar;
    String userID_image;
    String userID;
    String userName_image;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_image_detail);

        url = getIntent().getStringExtra("url");
        userID_image = getIntent().getStringExtra("userID");
        userName_image = getIntent().getStringExtra("userName");

        SharedPreferences sharedPreferences = getSharedPreferences("mine", Context.MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");
        userName = sharedPreferences.getString("userName", "null");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(userName_image);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Glide.with(getApplicationContext()).load(url).into(imageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (userID_image.equals(userID)) {
            getMenuInflater().inflate(R.menu.stamp_one_image_my_toolbar_menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.stamp_one_image_other_toolbar_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveButton:

                break;
            case R.id.deleteButton:
                deleteButtonClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    } //toolbar

    private void deleteButtonClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OneImageDetailActivity.this);
        builder.setMessage("스탬프를 삭제하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    private void delete() {

        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody urlBody = RequestBody.create(MediaType.parse("text/plain"), url);

        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.ImageDelete(userIDBody, urlBody);

        //finally performing the call
        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, Response<SuccessResponseInfo> response) {

                if (response.isSuccessful()) {

                    SuccessResponseInfo successResponseInfo = response.body();
                    if (successResponseInfo.getSuccess()) {
                        finish();
                    }


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {

            }
        });
    } // retrofit 데이터 받아오기


}
