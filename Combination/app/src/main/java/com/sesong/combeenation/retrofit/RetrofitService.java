package com.sesong.combeenation.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    public static final String BASE_URL="https://papicochat.herokuapp.com/";

    @FormUrlEncoded
    @POST("/users/signin")
    Call<JsonObject> getPost(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("/users/signup")
    Call<JsonObject> users(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("combinations")
    Call<JsonObject> combinations(
            @Field("title") String title,
            @Field("content") String content,
            @Field("imagePath") String image);
}