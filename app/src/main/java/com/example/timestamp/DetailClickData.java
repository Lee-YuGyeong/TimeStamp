package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailClickData extends AppCompatActivity {

    String title;
    int num;

    Toolbar toolbar;
    PeopleRecyclerAdapter adapter;
    TextView textView_peopleNum;

    DetailClickDataShare detailClickDataShare = new DetailClickDataShare();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_click_data);
        title = getIntent().getStringExtra("title");
        num = getIntent().getIntExtra("num", 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView_peopleNum = (TextView) findViewById(R.id.textView_peopleNum);

        if (getIntent().getIntExtra("share", -1) == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_click_container, new DetailClickDataMy()).commit();
        } else {

            getSupportFragmentManager().beginTransaction().replace(R.id.detail_click_container, detailClickDataShare).commit();
            Bundle bundle = new Bundle();
            bundle.putInt("num", num);
            detailClickDataShare.setArguments(bundle);
        }

//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new PeopleRecyclerAdapter(getApplicationContext());
//        recyclerView.setAdapter(adapter);

        //    getPeople();


    }

    private void getPeople() {

        SharedPreferences sharedPreferences = getSharedPreferences("mine", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "null");

        Api Api = APIClient.getClient().create(Api.class);
        Call<DetailClickDataResponseInfo> call = Api.PeopleGet(num);


        //finally performing the call
        call.enqueue(new Callback<DetailClickDataResponseInfo>() {
            @Override
            public void onResponse(Call<DetailClickDataResponseInfo> call, Response<DetailClickDataResponseInfo> response) {

                if (response.isSuccessful()) {

                    DetailClickDataResponseInfo detailClickDataResponseInfo = response.body();
                    List<DetailClickDataInfo> detailClickDataInfoList = new ArrayList<DetailClickDataInfo>(detailClickDataResponseInfo.getDetailClickDataInfoList());

                    //                 textView_peopleNum.setText(detailClickDataInfoList.size() + "명");
                    adapter.items.clear();

                    if (detailClickDataInfoList.size() != 0) {

                        for (int i = 0; i < detailClickDataInfoList.size(); i++) {
                            adapter.addItem(new PeopleItem(detailClickDataInfoList.get(i).getUserName()));
                        }


                    }
                    adapter.notifyDataSetChanged();

                } else { //response 실패
                }

            }

            @Override
            public void onFailure(Call<DetailClickDataResponseInfo> call, Throwable t) {

            }
        });
    } // retrofit 데이터 받아오기
}
