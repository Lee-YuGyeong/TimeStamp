package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.ui.stamp.StampAddActivity;
import com.example.timestamp.ui.stamp.StampDetailActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareSearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShareRecyclerAdapter shareRecyclerAdapter;

    Toolbar toolbar;

    ShareItem shareItem;


    @Override
    public void onResume() {
        super.onResume();
        getShareRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_search);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("공유스탬프");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        shareRecyclerAdapter = new ShareRecyclerAdapter(getApplicationContext());

        getShareRecyclerView();

        recyclerView.setAdapter(shareRecyclerAdapter);

        shareRecyclerAdapter.setOnItemClickListener(new ShareRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ShareRecyclerAdapter.ViewHolder holder, View view, int position) {

                Intent intent = new Intent(getApplicationContext(), ShareViewClickActivity.class);
                shareItem = shareRecyclerAdapter.getItem(position);
                intent.putExtra("shareItem", shareItem);
                startActivityForResult(intent, 1001);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stamp_detail_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.plusButton:
                Intent intent = new Intent(getApplicationContext(), StampAddActivity.class);
                startActivityForResult(intent, 1000);
                break;
        }
        return super.onOptionsItemSelected(item);
    } //toolbar


    private void getShareRecyclerView() {

        SharedPreferences sharedPreferences = getSharedPreferences("mine", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "null");

        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);

        Api Api = APIClient.getClient().create(Api.class);
        Call<ShareRecyclerViewResponseInfo> call = Api.ShareGet(userIDBody);

        //finally performing the call
        call.enqueue(new Callback<ShareRecyclerViewResponseInfo>() {
            @Override
            public void onResponse(Call<ShareRecyclerViewResponseInfo> call, Response<ShareRecyclerViewResponseInfo> response) {

                if (response.isSuccessful()) {

                    ShareRecyclerViewResponseInfo shareRecyclerViewResponseInfo = response.body();
                    List<ShareRecyclerViewInfo> shareRecyclerViewInfoList = new ArrayList<ShareRecyclerViewInfo>(shareRecyclerViewResponseInfo.getShareRecyclerViewInfoList());

                    shareRecyclerAdapter.items.clear();

                    if (shareRecyclerViewInfoList.size() != 0) {

                        for (int i = 0; i < shareRecyclerViewInfoList.size(); i++) {
                            shareRecyclerAdapter.addItem(new ShareItem(shareRecyclerViewInfoList.get(i).getNum(), shareRecyclerViewInfoList.get(i).getTitle(), shareRecyclerViewInfoList.get(i).getTitleImage(), shareRecyclerViewInfoList.get(i).getPeople(), shareRecyclerViewInfoList.get(i).getTag()));
                        }


                    }
                    shareRecyclerAdapter.notifyDataSetChanged();

                } else { //response 실패
                }

            }

            @Override
            public void onFailure(Call<ShareRecyclerViewResponseInfo> call, Throwable t) {

            }
        });
    } // retrofit 데이터 받아오기


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        } else if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "입장완료되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), StampDetailActivity.class);
                intent.putExtra("title", shareItem.getTitle());
                intent.putExtra("num", shareItem.getNum());
                startActivity(intent);
            }
        }
    }


}
