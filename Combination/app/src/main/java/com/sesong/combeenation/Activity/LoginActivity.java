package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton loginButton = findViewById(R.id.loginbutton);
        ImageButton signupButton = findViewById(R.id.signupButton);
        final EditText username_edit = findViewById(R.id.username_edit);
        final EditText password_edit = findViewById(R.id.password_edit);

        // SharedPreference에 데이터 저장
        //preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        String token = preferences.getString("token", null);
        if (token != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("token", token);
            startActivity(intent);
        }
        editor = preferences.edit();

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

    public void login(String username, final String password) {
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
                    JSONObject repo = null;
                    try {
                        repo = new JSONObject(String.valueOf(response.body()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String data = null;
                    if (repo != null) {
                        try {
                            data = repo.getString("token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    String jsondata = String.valueOf(data);
                    Log.d("before data ", data);
                    Log.d("jsondata    ", jsondata);

                    editor.putString("token", jsondata);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("token", jsondata);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Server connect Error ", String.valueOf(t));
            }
        });
    }
}
