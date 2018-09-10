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

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_edit.getText().toString();
                String password = password_edit.getText().toString();
                login(username, password);
            }
        });
    }

    public void login(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<JsonObject> response = retrofitService.getPost(username, password);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
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
                        JSONObject jObj = json.optJSONObject("token");
                        Log.d("JSONOBJECT   _1   ", String.valueOf(jObj));
                        String token_data = jObj.getString("data");
                        Log.d("JSONOBJECT   _2   ", token_data);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("token", token_data);
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
