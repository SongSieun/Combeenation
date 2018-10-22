package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.sesong.combeenation.Adapter.UpdateRecyclerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.item.CardItem;
import com.sesong.combeenation.retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String token;
    private TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        Log.d("Mypage token ", token);

        usernameText = findViewById(R.id.user_name);

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

        getUserInfo(token);
    }

    public void getUserInfo(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<JsonObject> response = retrofitService.info(token);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Response (body)", String.valueOf(response.body()));
                Log.d("Response (code)", String.valueOf(response.code()));
                Log.d("Response (message)", String.valueOf(response.message()));
                Log.d("Response (isSuccessful)", String.valueOf(response.isSuccessful()));

                JSONObject usernameResponse = null;
                try {
                    usernameResponse = new JSONObject(String.valueOf(response.body()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String username = null;
                try {
                    username = usernameResponse.getString("username");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("mypage_username", username);

                setUsernameTV(username);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("My page failure ", String.valueOf(t));
            }
        });
    }

    private void setUsernameTV(final String username) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                usernameText.setText(username);
            }
        });
    }
}