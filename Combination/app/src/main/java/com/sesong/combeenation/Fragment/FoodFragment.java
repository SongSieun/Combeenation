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

public class FoodFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("간단야식 밴쯔정식", "조각피자 + 훈제 닭다리 + 스트링치즈 + 편의점 떡볶이"));
        dataList.add(new CardItem("편의점 마크정식", "스파게티 라면 + 편의점 떡볶이 + 마늘후랑크 + 소시지 + 치즈"));
        dataList.add(new CardItem("간단야식 밴쯔정식", "조각피자 + 훈제 닭다리 + 스트링치즈 + 편의점 떡볶이"));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
