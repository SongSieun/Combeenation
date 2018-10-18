package com.sesong.combeenation.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    public static final String BASE_URL="https://combeenation.herokuapp.com";

    @FormUrlEncoded
    //@Headers("Content-Type : application/json")
    @POST("/users/login")
    Call<JsonObject> signin(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    //@Headers("Content-Type : application/json")
    @POST("/users/join")
    Call<JsonObject> signup(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @GET("/users/info")
    Call<JsonObject> info(
            @Header("token") String token);

    @FormUrlEncoded
    @POST("combination")
    Call<JsonObject> combinations(
            @Field("title") String title,
            @Field("content") String content,
            @Field("imagePath") String image);
}