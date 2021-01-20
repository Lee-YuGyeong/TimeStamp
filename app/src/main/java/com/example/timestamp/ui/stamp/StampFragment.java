package com.example.timestamp.ui.stamp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.MenuInfo;
import com.example.timestamp.MenuResponseInfo;
import com.example.timestamp.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StampFragment extends Fragment {

    GridView gridView;
    StampMenuAdapter adapter;

    String userID;

    ImageView imageView_people;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_stamp, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true); //toolbar

        getUserInfo();
        getMenuList();

        imageView_people = (ImageView) root.findViewById(R.id.imageView_people);

        gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new StampMenuAdapter();

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getContext(), StampDetailActivity.class);
                intent.putExtra("title", adapter.items.get(position).getTitle());
                intent.putExtra("num", adapter.items.get(position).getNum());

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("mine", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("share", adapter.items.get(position).getShare());
                editor.commit();
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
        inflater.inflate(R.menu.stamp_toolbar_menu, menu);
    } //toolbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.plusButton:
                Intent intent = new Intent(getContext(), AddSelectActivity.class);
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
        Call<MenuResponseInfo> call = Api.MenuGet(userIDBody);

        //finally performing the call
        call.enqueue(new Callback<MenuResponseInfo>() {
            @Override
            public void onResponse(Call<MenuResponseInfo> call, Response<MenuResponseInfo> response) {

                if (response.isSuccessful()) {

                    MenuResponseInfo menuResponseInfo = response.body();
                    List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>(menuResponseInfo.getMenuInfoList());

                    adapter.items.clear();

                    if (adapter.isEmpty() && menuInfoList.size() != 0) {

                        for (int i = 0; i < menuInfoList.size(); i++) {
                            adapter.addItem(new StampMenuGridItem(menuInfoList.get(i).getTitleImage(), menuInfoList.get(i).getTitle(), menuInfoList.get(i).getColor(), menuInfoList.get(i).getNum(), menuInfoList.get(i).getShare()));
                        }

                    }
                    adapter.notifyDataSetChanged();

                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<MenuResponseInfo> call, Throwable t) {

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
        ArrayList<StampMenuGridItem> items = new ArrayList<StampMenuGridItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(StampMenuGridItem item) {
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
            StampMenuGridItemView view = null;
            if (convertView == null) {
                view = new StampMenuGridItemView(getContext());
            } else {
                view = (StampMenuGridItemView) convertView;
            }

            StampMenuGridItem item = items.get(position);
            view.setTitle(item.getTitle());
            view.setImage(item.getImage());
            view.setPeople(item.getShare());
            view.setColor(item.getColor());

            return view;
        }
    }


}

