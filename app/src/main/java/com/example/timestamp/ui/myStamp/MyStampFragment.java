package com.example.timestamp.ui.myStamp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.timestamp.MyMenuInfo;
import com.example.timestamp.MyResponse;
import com.example.timestamp.R;
import com.example.timestamp.ResponseInfo;
import com.example.timestamp.RetrofitClient;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStampFragment extends Fragment {

    GridView gridView;
    StampMenuAdapter adapter;

    String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_stamp, container, false);

        getUserInfo();
        getMenuList();

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyStampAddActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new StampMenuAdapter();

//        adapter.addItem(new MyStampMenuGridItem("background1.jpg", "스터디"));
//        adapter.addItem(new MyStampMenuGridItem("background2.jpg", "다이어트"));
//        adapter.addItem(new MyStampMenuGridItem("background3.jpg", "기상"));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getContext(), MyStampDetailActivity.class);
                intent.putExtra("title", adapter.items.get(position).getTitle());
                startActivity(intent);
            }
        });

        return root;
    }

    public void getUserInfo() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("mine", Context.MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");

    }

    private void getMenuList() {
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        Call<ResponseInfo> call = RetrofitClient.getInstance().getApi().getimages(userIDBody);

        //finally performing the call
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

                if (response.isSuccessful()) {
                    ResponseInfo myResponseList = response.body();
                    List<MyMenuInfo> myMenuInfoList = new ArrayList<MyMenuInfo>(myResponseList.getMyMenuInfoList());


                    for (int i = 0; i < myMenuInfoList.size(); i++) {
                        adapter.addItem(new MyStampMenuGridItem(myMenuInfoList.get(i).getMyTitleImage(), myMenuInfoList.get(i).getMyTitle()));

                        Log.d("아아",i+ myMenuInfoList.get(i).getMyTitle());
                    }
                    adapter.notifyDataSetChanged();


                } else {
                }

            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
            }
        });
    } // retrofit 데이터 받아오기


    private Bitmap resize(Bitmap bm) {
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 800)
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        else if (config.smallestScreenWidthDp >= 600)
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        else if (config.smallestScreenWidthDp >= 400)
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        else if (config.smallestScreenWidthDp >= 360)
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        else
            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        return bm;
    }


    class StampMenuAdapter extends BaseAdapter {
        ArrayList<MyStampMenuGridItem> items = new ArrayList<MyStampMenuGridItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MyStampMenuGridItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyStampMenuGridItemView view = null;
            if (convertView == null) {
                view = new MyStampMenuGridItemView(getContext());
            } else {
                view = (MyStampMenuGridItemView) convertView;
            }

            MyStampMenuGridItem item = items.get(position);
            view.setTitle(item.getTitle());
            view.setImage(item.getImage());


            return view;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 0) {
            if (intent != null) {
//                Bitmap bitmap = intent.get("bitmap", 0);
//                String title = intent.getStringExtra("title");
//
//                adapter.addItem(new MyStampMenuGridItem(bitmap, title));
//
//                adapter.notifyDataSetChanged();
            }
        }
    }

}

