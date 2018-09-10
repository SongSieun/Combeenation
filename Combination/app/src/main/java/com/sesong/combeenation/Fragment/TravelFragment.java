package com.sesong.combeenation.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sesong.combeenation.Adapter.MyRecyclerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.item.CardItem;

import java.util.ArrayList;
import java.util.List;

public class TravelFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, null);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager);
        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("한옥마을 전주여행", "전주한옥마을 + 그 옆에 시장 + 시내에 있는 노래방 + 전주명동성당"));
        dataList.add(new CardItem("역사 속 서울여행", "광화문 + 명동 + 홍대 + 서울역 + 벽화거리"));
        dataList.add(new CardItem("해커톤 개발여행", "해카톤 + 하이톤 + 아이스 + 벌"));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView2.setAdapter(adapter);
        return view;
    }
}
