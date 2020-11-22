package com.example.timestamp.ui.myStamp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.timestamp.R;
import com.example.timestamp.StampTitleImageItemView;

import java.util.ArrayList;

public class MyStampDetailActivity extends AppCompatActivity {

    StampDetailAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stamp_detail);

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new StampDetailAdapter();

        adapter.addItem(new MyStampDetailGridItem(R.drawable.background1));
        adapter.addItem(new MyStampDetailGridItem(R.drawable.background2));
        adapter.addItem(new MyStampDetailGridItem(R.drawable.background3));
        adapter.addItem(new MyStampDetailGridItem(R.drawable.background2));
        adapter.addItem(new MyStampDetailGridItem(R.drawable.background3));
        adapter.addItem(new MyStampDetailGridItem(R.drawable.background1));
        adapter.addItem(new MyStampDetailGridItem(R.drawable.background1));

        gridView.setAdapter(adapter);

    }

    class StampDetailAdapter extends BaseAdapter {

        ArrayList<MyStampDetailGridItem> items = new ArrayList<MyStampDetailGridItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MyStampDetailGridItem item) {
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
            MyStampDetailGridItemView view = null;
            if (convertView == null) {
                view = new MyStampDetailGridItemView(getApplicationContext());
            } else {
                view = (MyStampDetailGridItemView) convertView;
            }

            MyStampDetailGridItem item = items.get(position);

            view.setImage(item.getImage());

            return view;

        }
    }

}
