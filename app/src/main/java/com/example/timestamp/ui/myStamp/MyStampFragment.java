package com.example.timestamp.ui.myStamp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

        final GridView gridView = (GridView) root.findViewById(R.id.gridView);

        adapter = new StampMenuAdapter();

//        Bitmap bit = BitmapFactory.decodeResource(getActivity().getResources(),
//                R.drawable.background1);
//        resize(bit);

//       adapter.addItem(new MyStampMenuGridItem(bit, "스터디"));
//        adapter.addItem(new MyStampMenuGridItem(bit, "다이어트"));
//        adapter.addItem(new MyStampMenuGridItem(bit, "기상"));
//        adapter.addItem(new MyStampMenuGridItem(bit, "스터디"));
//        adapter.addItem(new MyStampMenuGridItem(bit, "스터디"));
//        adapter.addItem(new MyStampMenuGridItem(bit, "스터디"));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getContext(),MyStampDetailActivity.class);
                intent.putExtra("title",adapter.items.get(position).getTitle());
                startActivity(intent);
            }
        });

        return root;
    }

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
            view.setImage(item.getResId());


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

