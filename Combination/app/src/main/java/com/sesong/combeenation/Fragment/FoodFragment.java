package com.sesong.combeenation.Fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sesong.combeenation.Adapter.MyRecyclerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.TokenData;
import com.sesong.combeenation.databinding.FragmentFoodBinding;
import com.sesong.combeenation.item.CardItem;
import com.sesong.combeenation.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodFragment extends Fragment {
    private String type = "food";
    private List<CardItem> dataList;
    private FragmentFoodBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);

        dataList = new ArrayList<>();
        String token = TokenData.getInstance().getToken();
        Log.d("Food Fragment token ", token); // NullPointer
        getCombination(token);

        return binding.getRoot();
    }

    public void getCombination(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        final Call<JsonObject> response = retrofitService.getContent(token, type);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("FFResponse (body)", String.valueOf(response.body()));
                Log.d("FFResponse (code)", String.valueOf(response.code()));
                Log.d("FFResponse (message)", String.valueOf(response.message()));
                Log.d("FFResponse (isSucce)", String.valueOf(response.isSuccessful()));

                JsonArray typeContent = response.body().getAsJsonArray("result");

                for (int i = 0; i < typeContent.size(); i++){
                    JsonObject itemObject = (JsonObject) typeContent.get(i);
                    Log.d("itemObject ", String.valueOf(itemObject));
                    // String image = String.valueOf(itemObject.get("image"));
                    // Log.d("imageString ", image);
                    String name = String.valueOf(itemObject.get("name"));
                    Log.d("nameString ", name);
                    String type = String.valueOf(itemObject.get("type"));
                    Log.d("typeString ", type);
                    String combination = String.valueOf(itemObject.get("combination"));
                    Log.d("combinationString ", combination);

                    dataList.add(new CardItem(name, combination, R.drawable.picture));
                    binding.recyclerView.setAdapter(new MyRecyclerAdapter(getActivity().getApplicationContext(),dataList));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("getCombination Failed!", String.valueOf(response));
                t.printStackTrace();
            }
        });
    }

    /*private Bitmap getBitmapFromString(String jsonString) {
        byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }*/
}