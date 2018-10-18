package com.sesong.combeenation.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sesong.combeenation.R;

public class MypageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        // 레이아웃 매니저로 LinearLayoutManager를 설정
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

    }
}

