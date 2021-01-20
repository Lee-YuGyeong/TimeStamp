package com.example.timestamp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PeopleRecyclerAdapter extends RecyclerView.Adapter<PeopleRecyclerAdapter.ViewHolder> {

    Context context;

    ArrayList<PeopleItem> items = new ArrayList<PeopleItem>();

    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void OnItemClick(ViewHolder holder, View view, int position);
    }

    public PeopleRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public int getItemCount() {
        return items.size();
    }//아이템 갯수


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.people_recycler_view_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PeopleItem item = items.get(position);
        holder.setItem(item);

        holder.setOnItemClickListener(listener);
    }

    public void addItem(PeopleItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<PeopleItem> items) {
        this.items = items;
    }

    public PeopleItem getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }//클릭 이벤트

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.OnItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(PeopleItem item) {
            textView.setText(item.getName());

        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }
}