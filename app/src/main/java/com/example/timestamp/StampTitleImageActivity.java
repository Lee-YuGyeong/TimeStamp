package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;


import java.util.ArrayList;

public class StampTitleImageActivity extends AppCompatActivity {

    StampTitleImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_title_image);


        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new StampTitleImageAdapter();

        adapter.addItem(new StampTitleImageItem(R.drawable.background1));
        adapter.addItem(new StampTitleImageItem(R.drawable.background2));
        adapter.addItem(new StampTitleImageItem(R.drawable.background3));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("resId", adapter.items.get(position).getResId());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    class StampTitleImageAdapter extends BaseAdapter {

        ArrayList<StampTitleImageItem> items = new ArrayList<StampTitleImageItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(StampTitleImageItem item) {
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
            StampTitleImageItemView view = null;
            if (convertView == null) {
                view = new StampTitleImageItemView(getApplicationContext());
            } else {
                view = (StampTitleImageItemView) convertView;
            }

            StampTitleImageItem item = items.get(position);

            view.setImage(item.getResId());

            return view;

        }
    }
}
