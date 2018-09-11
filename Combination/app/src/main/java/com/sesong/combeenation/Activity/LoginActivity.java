package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sesong.combeenation.R;
import com.sesong.combeenation.retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton loginButton = (ImageButton) findViewById(R.id.loginbutton);
        ImageButton signupButton = (ImageButton) findViewById(R.id.signupButton);
        final EditText username_edit = (EditText) findViewById(R.id.username_edit);
        final EditText password_edit = (EditText) findViewById(R.id.password_edit);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_edit.getText().toString();
                String password = password_edit.getText().toString();
                login(username, password);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<JsonObject> response = retrofitService.signin(username, password);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Response (body)", String.valueOf(response.body()));
                Log.d("Response (code)", String.valueOf(response.code()));
                Log.d("Response (message)", String.valueOf(response.message()));
                Log.d("Response (isSuccessful)", String.valueOf(response.isSuccessful()));
                if (response.code() == 400) {
                    Log.d("Login", "failure ");
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Login", "Pass ");
                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //startActivity(intent);
                    JsonObject repo = response.body();
                    try {
                        JSONObject json = new JSONObject(String.valueOf(response.body()));
                        Log.d("JSONOBJECT   ", String.valueOf(json));
                        Boolean result_sucess = json.getBoolean("success");
                        Log.d("JSONOBJECT_SUCCESS : ", String.valueOf(result_sucess));
                        String result_message = json.getString("message");
                        Log.d("JSONOBJECT_MESSAGE : ", result_message);
                        String result_token = json.getString("token");
                        Log.d("JSONOBJECT_TOKEN : ", result_token);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("token", result_token);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Log.d("responselog", String.valueOf(repo));
                    //JsonObject data = repo.getAsJsonObject("token").getAsJsonObject("data");
                    //String jsondata = String.valueOf(data);
                    //Log.d("jsondata    ", jsondata);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
