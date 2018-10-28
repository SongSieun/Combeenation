package com.sesong.combeenation.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sesong.combeenation.Adapter.MyRecyclerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.TokenData;
import com.sesong.combeenation.item.CardItem;
import com.sesong.combeenation.retrofit.RetrofitService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        /*String token = ((TokenData) getActivity().getApplication()).getToken();
        Log.d("Food Fragment token ", token);
        addMenu(token);*/

        /*MyRecyclerAdapter adapter = new MyRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);*/
        return view;
    }

    public void addMenu(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        final Call<JsonArray> response = retrofitService.getContent(token, "food");
        response.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.d("Response (body)", String.valueOf(response.body()));
                Log.d("Response (code)", String.valueOf(response.code()));
                Log.d("Response (message)", String.valueOf(response.message()));
                Log.d("Response (isSuccessful)", String.valueOf(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("addMenu Failed!!!", String.valueOf(response));
                t.printStackTrace();
            }
        });
    }
}
