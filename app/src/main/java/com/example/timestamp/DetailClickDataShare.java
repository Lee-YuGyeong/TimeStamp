package com.example.timestamp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailClickDataShare extends Fragment {

    int num;

    PeopleRecyclerAdapter adapter;
    TextView textView_peopleNum;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail_click_data_share, container, false);

        num = getArguments().getInt("num");

        textView_peopleNum = (TextView) root.findViewById(R.id.textView_peopleNum);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PeopleRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        getPeople();


        return root;
    }

    private void getPeople() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("mine", Context.MODE_PRIVATE);
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

                    textView_peopleNum.setText(detailClickDataInfoList.size() + "명");
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
