package com.sesong.combeenation.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService_sign {
    public static final String BASE_URL="http://gomth.zapto.org:3000/";

    @FormUrlEncoded
    @POST("users")
    Call<ResponseBody> getPost(
            @Field("username") String username,
            @Field("password") String password);
}