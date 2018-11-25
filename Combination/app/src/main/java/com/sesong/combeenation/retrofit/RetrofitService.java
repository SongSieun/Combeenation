package com.sesong.combeenation.retrofit;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    public static final String BASE_URL = "https://combeenation.herokuapp.com";

    @FormUrlEncoded
    @POST("/users/login")
    Call<JsonObject> signin(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("/users/join")
    Call<JsonObject> signup(
            @Field("username") String username,
            @Field("password") String password);

    @GET("/users/info")
    Call<JsonObject> info(
            @Header("token") String token);

    @FormUrlEncoded
    @POST("/combinations")
    Call<JsonObject> combinations(
            @Header("token") String token,
            @Field("name") String name,
            // @Field("image") String image,
            @Field("combination") String combination,
            @Field("type") String type);

    @GET("/combinations/type/{type}")
    Call<JsonObject> getContent(
            @Header("token") String token,
            @Path("type") String type);
}