package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.ui.stamp.StampAdd_ShareActivity;
import com.example.timestamp.ui.stamp.StampDetailGridItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareSearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShareRecyclerAdapter shareRecyclerAdapter;

    Toolbar toolbar;

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
//        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(48));
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
//                LinearLayoutManager.VERTICAL);
//        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_view_line));
//        recyclerView.addItemDecoration(dividerItemDecoration);
        shareRecyclerAdapter = new ShareRecyclerAdapter(getApplicationContext());

        getShareRecyclerView();

        recyclerView.setAdapter(shareRecyclerAdapter);

        shareRecyclerAdapter.setOnItemClickListener(new ShareRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ShareRecyclerAdapter.ViewHolder holder, View view, int position) {
                ShareItem item = shareRecyclerAdapter.getItem(position);

                Toast.makeText(getApplicationContext(), "아이템 선택됨 : " + item.getTitle(), Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(getApplicationContext(), StampAdd_ShareActivity.class);
                startActivityForResult(intent, 1000);
                break;
        }
        return super.onOptionsItemSelected(item);
    } //toolbar


    private void getShareRecyclerView() {

        Api Api = APIClient.getClient().create(Api.class);
        Call<ShareRecyclerViewResponseInfo> call = Api.ShareGet(1);

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
                            shareRecyclerAdapter.addItem(new ShareItem(shareRecyclerViewInfoList.get(i).getTitle(), shareRecyclerViewInfoList.get(i).getTitleImage(), shareRecyclerViewInfoList.get(i).getPeople()));
                        }

                        shareRecyclerAdapter.notifyDataSetChanged();

                    }


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
        }
    }


}
