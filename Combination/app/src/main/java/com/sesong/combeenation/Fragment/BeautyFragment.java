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

public class BeautyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty, null);
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_view3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(layoutManager);
        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("수분 보충 로드샵 루틴", "스킨 + 로션 + 수분크림 + 에센스 + 팩"));
        dataList.add(new CardItem("백화점 브랜드 메이크업", "VDL + 에스쁘아 + 백화점 + 흐힛 + 뀨"));
        dataList.add(new CardItem("여드름 케어", "여드름을 + 짤때는 + 손씻고 + 조심조심"));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView1.setAdapter(adapter);
        return view;
    }
}
