package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import com.example.timestamp.ui.stamp.StampDetailActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareViewClickActivity extends AppCompatActivity {

    Toolbar toolbar;

    ShareItem shareItem;



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

        TextView textView_title = (TextView) findViewById(R.id.textView_title);
        TextView textView_people = (TextView) findViewById(R.id.textView_people);
        TextView textView_tag = (TextView) findViewById(R.id.textView_tag);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        shareItem = (ShareItem) getIntent().getSerializableExtra("shareItem");
        textView_title.setText(shareItem.getTitle());
        textView_people.setText(shareItem.getPeople() + "명");
        textView_tag.setText(shareItem.getTag());
        Glide.with(getApplicationContext()).load(shareItem.getTitleImage()).into(imageView);

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
        int num = shareItem.getNum();

        Api Api = APIClient.getClient().create(Api.class);
        Call<ErrorResponseInfo> call = Api.ShareUpload(userIDBody, num);

        //finally performing the call
        call.enqueue(new Callback<ErrorResponseInfo>() {
            @Override
            public void onResponse(Call<ErrorResponseInfo> call, Response<ErrorResponseInfo> response) {

                if (response.isSuccessful()) {

                    ErrorResponseInfo errorResponseInfo = response.body();
                    if (!errorResponseInfo.isError()) {
                        setResult(RESULT_OK);
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
