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

public class ShareRecyclerAdapter extends RecyclerView.Adapter<ShareRecyclerAdapter.ViewHolder> {

    Context context;

    ArrayList<ShareItem> items = new ArrayList<ShareItem>();

    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void OnItemClick(ViewHolder holder, View view, int position);
    }

    public ShareRecyclerAdapter(Context context) {
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
        View itemView = inflater.inflate(R.layout.search_recycler_view_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShareItem item = items.get(position);
        holder.setItem(item);

        holder.setOnItemClickListener(listener);
    }

    public void addItem(ShareItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<ShareItem> items) {
        this.items = items;
    }

    public ShareItem getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }//클릭 이벤트

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        ImageView imageView;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            textView3 = (TextView) itemView.findViewById(R.id.textView3);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

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

        public void setItem(ShareItem item) {
            textView.setText(item.getTitle());
            textView2.setText(item.getPeople()+"명");
            textView3.setText(item.getTag());
            Glide.with(imageView.getContext()).load(item.getTitleImage()).into(imageView);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }
}