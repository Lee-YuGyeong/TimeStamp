package com.example.timestamp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.timestamp.ui.myStamp.MyStampDetailGridItem;
import com.example.timestamp.ui.myStamp.MyStampDetailGridItemView;

import java.util.ArrayList;

public class TimeStyleButtonFragment2 extends Fragment {

    FragmentCallBack callBack;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

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

        View root = inflater.inflate(R.layout.fragment_time_style_button2, container, false);

        gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new TimeStyleAdapter();

        adapter.addItem(new TimeStyleItem("없음"));
        adapter.addItem(new TimeStyleItem("자세한 시간"));
        adapter.addItem(new TimeStyleItem("간단한 시간"));
        adapter.addItem(new TimeStyleItem("영어"));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                callBack.TimeStyleButtonSelected("style", position);

            }
        });// 메뉴 그리드뷰
//
//        button1 = (Button) root.findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callBack != null) {
//                    callBack.TimeStyleButtonSelected("style", 1);
//                }
//                buttonNotSelect();
//                buttonSelect(button1);
//            }
//        });
//
//        button2 = (Button) root.findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callBack != null) {
//                    callBack.TimeStyleButtonSelected("style", 2);
//                }
//                buttonNotSelect();
//                buttonSelect(button2);
//            }
//        });
//
//        button3 = (Button) root.findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callBack != null) {
//                    callBack.TimeStyleButtonSelected("style", 3);
//                }
//                buttonNotSelect();
//                buttonSelect(button3);
//            }
//        });
//
//        button4 = (Button) root.findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callBack != null) {
//                    callBack.TimeStyleButtonSelected("style", 4);
//                }
//                buttonNotSelect();
//                buttonSelect(button4);
//            }
//        });
//
//        button5 = (Button) root.findViewById(R.id.button5);
//        button6 = (Button) root.findViewById(R.id.button6);
//
//        callBack.TimeStyleButtonSelected("style", 1);
//        buttonNotSelect();
//        buttonSelect(button1);
//
        return root;
    }

//    public void buttonSelect(Button button) {
//        button.setBackgroundResource(R.drawable.button_border_click);
//    }
//
//    public void buttonNotSelect() {
//        button1.setBackgroundResource(R.drawable.button_border);
//        button2.setBackgroundResource(R.drawable.button_border);
//        button3.setBackgroundResource(R.drawable.button_border);
//        button4.setBackgroundResource(R.drawable.button_border);
//        button5.setBackgroundResource(R.drawable.button_border);
//        button6.setBackgroundResource(R.drawable.button_border);
//    }

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
