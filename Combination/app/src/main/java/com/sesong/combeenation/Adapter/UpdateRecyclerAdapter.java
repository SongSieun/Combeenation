package com.sesong.combeenation.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sesong.combeenation.R;
import com.sesong.combeenation.item.CardItem;

import java.util.List;

public class UpdateRecyclerAdapter extends RecyclerView.Adapter<UpdateRecyclerAdapter.ViewHolder> {
    private final List<CardItem> mDataList;

    public UpdateRecyclerAdapter(List<CardItem> dataList) {
        mDataList = dataList;
    }

    // 뷰 홀더를 생성하는 부분. 레이아웃을 만드는 부분
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_update_item, parent, false);
        return new ViewHolder(view);
    }

    // 뷰 홀더에 데이터를 설정하는 부분
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());
    }

    // 아이템의 수
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    // 각각의 아이템의 레퍼런스를 저장할 뷰 홀더 클래스
    // 반드시 RecyclerView.ViewHolder를 상속해야 함
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.my_title_text);
            contents = itemView.findViewById(R.id.my_content_text);
        }
    }
}
