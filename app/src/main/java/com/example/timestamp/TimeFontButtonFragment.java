package com.example.timestamp;

import android.content.Context;
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
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TimeFontButtonFragment extends Fragment {

    FragmentCallBack callBack;

    TimeStyleAdapter adapter;
    GridView gridView;


    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        if (context instanceof FragmentCallBack) {
            callBack = (FragmentCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (callBack != null)
            callBack = null;

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_font_button, container, false);

        gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new TimeStyleAdapter();


        adapter.addItem(new TimeStyleItem("나눔바른펜"));
        adapter.addItem(new TimeStyleItem("나눔스퀘어"));
        adapter.addItem(new TimeStyleItem("마루부리"));
        adapter.addItem(new TimeStyleItem("배민한나"));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                callBack.TimeFontButtonSelected("font", position);
            }
        });// 메뉴 그리드뷰


        return root;
    }

    class TimeStyleAdapter extends BaseAdapter {

        ArrayList<TimeStyleItem> items = new ArrayList<TimeStyleItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(TimeStyleItem item) {
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
            TimeStyleItemView view = null;
            if (convertView == null) {
                view = new TimeStyleItemView(getContext());
            } else {
                view = (TimeStyleItemView) convertView;
            }

            TimeStyleItem item = items.get(position);

            view.setText(item.getText());

            return view;

        }
    }

}
