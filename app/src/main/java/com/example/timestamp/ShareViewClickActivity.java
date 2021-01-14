package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareViewClickActivity extends AppCompatActivity {

    String title;
    String titleImage;
    int people;
    int num;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_view_click);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = getIntent().getStringExtra("title");
        titleImage = getIntent().getStringExtra("titleImage");
        people = getIntent().getIntExtra("people", 0);
        num = getIntent().getIntExtra("num", 0);

        TextView textView_title = (TextView) findViewById(R.id.textView_title);
        TextView textView_people = (TextView) findViewById(R.id.textView_people);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        textView_title.setText(title);
        textView_people.setText(people + "명");
        Glide.with(getApplicationContext()).load(titleImage).into(imageView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadShareRecyclerView();
            }
        });

    }

    private void uploadShareRecyclerView() {

        SharedPreferences sharedPreferences = getSharedPreferences("mine", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "null");

        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);

        Api Api = APIClient.getClient().create(Api.class);
        Call<ErrorResponseInfo> call = Api.ShareUpload(userIDBody, num);

        //finally performing the call
        call.enqueue(new Callback<ErrorResponseInfo>() {
            @Override
            public void onResponse(Call<ErrorResponseInfo> call, Response<ErrorResponseInfo> response) {

                if (response.isSuccessful()) {

                    ErrorResponseInfo errorResponseInfo = response.body();
                    if (!errorResponseInfo.isError()) {
                        finish();
                    }

                } else { //response 실패
                }

            }

            @Override
            public void onFailure(Call<ErrorResponseInfo> call, Throwable t) {

            }
        });
    } // retrofit 데이터 받아오기


}
