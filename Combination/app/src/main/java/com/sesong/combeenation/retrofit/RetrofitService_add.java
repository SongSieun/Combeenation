package com.sesong.combeenation.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService_add {
    public static final String BASE_URL="http://gomth.zapto.org:3000/";

    @FormUrlEncoded
    @POST("users")
    Call<ResponseBody> getPost(
            @Field("title") String title,
            @Field("content") String content,
            @Field("imagePath") String image);
}