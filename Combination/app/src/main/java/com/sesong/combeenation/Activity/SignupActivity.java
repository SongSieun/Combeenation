package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.JsonObject;
import com.sesong.combeenation.R;
import com.sesong.combeenation.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageButton signupButton = (ImageButton)findViewById(R.id.signupbutton);
        final EditText id_edit = (EditText)findViewById(R.id.username_edit);
        final EditText pass_edit = (EditText)findViewById(R.id.Password_edit);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = id_edit.getText().toString();
                String Password_e = pass_edit.getText().toString();
                Log.d("ID : ", ID);
                Log.d("PassWord : ", Password_e);
                signup(ID, Password_e);
            }
        });
    }

    public void signup(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<JsonObject> response = retrofitService.users(username, password);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Login", "Pass ");
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}

