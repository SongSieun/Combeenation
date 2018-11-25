package com.sesong.combeenation.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sesong.combeenation.item.CardItem;
import com.sesong.combeenation.R;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private final List<CardItem> mDataList;
    Context context;

    public MyRecyclerAdapter(Context context, List<CardItem> dataList) {
        this.context = context;
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());
        holder.imageView.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            contents = itemView.findViewById(R.id.content_text);
            imageView = itemView.findViewById(R.id.itemImage);
        }
    }
}
