package com.sesong.combeenation.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sesong.combeenation.Adapter.UpdateRecyclerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.item.CardItem;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        // 레이아웃 매니저로 LinearLayoutManager를 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 표시할 임시 데이터
        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("첫번째 아이템", "안드로이드1 입니다"));
        dataList.add(new CardItem("두번째 아이템", "안드로이드2 입니다"));
        dataList.add(new CardItem("세번째 아이템", "안드로이드3 입니다"));
        dataList.add(new CardItem("네번째 아이템", "안드로이드4 입니다"));
        // 어댑터 설정
        UpdateRecyclerAdapter adapter = new UpdateRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);


    }
}

