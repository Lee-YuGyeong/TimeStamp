package com.example.timestamp.ui.myStamp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.MyMenuInfo;
import com.example.timestamp.R;
import com.example.timestamp.ResponseInfo;

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
    int myNum;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_stamp, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true); //toolbar

        getUserInfo();
        getMenuList();


        gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new StampMenuAdapter();

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getContext(), MyStampDetailActivity.class);
                intent.putExtra("title", adapter.items.get(position).getTitle());
                intent.putExtra("myNum", adapter.items.get(position).getNum());
                startActivity(intent);
            }
        });// 메뉴 그리드뷰

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
        getMenuList();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_stamp_toolbar_menu, menu);
    } //toolbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.plusButton:
                Intent intent = new Intent(getContext(), MyStampAddActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    } //toolbar


    public void getUserInfo() {

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("mine", Context.MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");

    }

    private void getMenuList() {
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);

        Api Api = APIClient.getClient().create(Api.class);
        Call<ResponseInfo> call = Api.MyMenuGet(userIDBody);

        //finally performing the call
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

                if (response.isSuccessful()) {
                    ResponseInfo myResponseList = response.body();
                    List<MyMenuInfo> myMenuInfoList = new ArrayList<MyMenuInfo>(myResponseList.getMyMenuInfoList());

                    adapter.items.clear();

                    if (adapter.isEmpty() && myMenuInfoList.size() != 0) {

                        for (int i = 0; i < myMenuInfoList.size(); i++) {
                            adapter.addItem(new MyStampMenuGridItem(myMenuInfoList.get(i).getMyTitleImage(), myMenuInfoList.get(i).getMyTitle(), myMenuInfoList.get(i).getMyNum()));
                        }

                    }
                    adapter.notifyDataSetChanged();

                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {

            }
        });
    } // retrofit 데이터 받아오기


//    private Bitmap resize(Bitmap bm) {
//        Configuration config = getResources().getConfiguration();
//        if (config.smallestScreenWidthDp >= 800)
//            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
//        else if (config.smallestScreenWidthDp >= 600)
//            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
//        else if (config.smallestScreenWidthDp >= 400)
//            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
//        else if (config.smallestScreenWidthDp >= 360)
//            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
//        else
//            bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
//        return bm;
//    }


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


}

