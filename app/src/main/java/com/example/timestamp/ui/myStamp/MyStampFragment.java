package com.example.timestamp.ui.myStamp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timestamp.R;

import java.util.ArrayList;

public class MyStampFragment extends Fragment {

    StampMenuAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_stamp, container, false);

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyStampAddActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        GridView gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new StampMenuAdapter();

        adapter.addItem(new MyStampMenuGridItem(R.drawable.background1, "스터디"));
        adapter.addItem(new MyStampMenuGridItem(R.drawable.background2, "다이어트"));
        adapter.addItem(new MyStampMenuGridItem(R.drawable.background3, "기상"));
        adapter.addItem(new MyStampMenuGridItem(R.drawable.background1, "스터디"));
        adapter.addItem(new MyStampMenuGridItem(R.drawable.background1, "스터디"));
        adapter.addItem(new MyStampMenuGridItem(R.drawable.background1, "스터디"));

        gridView.setAdapter(adapter);

        return root;
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
            view.setImage(item.getResId());


            return view;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 0) {
            if (intent != null) {
                int resId = intent.getIntExtra("resId", 0);
                String title = intent.getStringExtra("title");

                adapter.addItem(new MyStampMenuGridItem(resId, title));

                adapter.notifyDataSetChanged();
            }
        }
    }

}

