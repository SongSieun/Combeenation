package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.sesong.combeenation.Adapter.UpdateRecyclerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.TokenData;
import com.sesong.combeenation.databinding.ActivityMypageBinding;
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
    private ActivityMypageBinding binding;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mypage);

        token = TokenData.getInstance().getToken();

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        // 레이아웃 매니저로 LinearLayoutManager를 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 표시할 임시 데이터
        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("첫번째 아이템", "안드로이드1 입니다", R.drawable.beauty));
        dataList.add(new CardItem("두번째 아이템", "안드로이드2 입니다", R.drawable.picture));
        dataList.add(new CardItem("세번째 아이템", "안드로이드3 입니다", R.drawable.trip));
        dataList.add(new CardItem("네번째 아이템", "안드로이드4 입니다", R.drawable.trip));
        // 어댑터 설정
        UpdateRecyclerAdapter adapter = new UpdateRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        binding.settingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(MypageActivity.this, SettingsActivity.class);
                startActivity(settingIntent);
            }
        });

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
                Log.d("MyPage_Response (body)", String.valueOf(response.body()));
                Log.d("MyPage_Response (code)", String.valueOf(response.code()));
                Log.d("MyPage_Response (mess)", String.valueOf(response.message()));
                Log.d("MyPage_Response (isSu)", String.valueOf(response.isSuccessful()));

                JSONObject usernameResponse = null;
                try {
                    usernameResponse = new JSONObject(String.valueOf(response.body()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String username = null;
                try {
                    username = usernameResponse.getString("username");
                    setUsernameTV(username);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("mypage_username", username);
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
                binding.usernameText.setText(username);
            }
        });
    }
}