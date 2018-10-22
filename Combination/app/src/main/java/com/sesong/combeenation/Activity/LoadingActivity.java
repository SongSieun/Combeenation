package com.sesong.combeenation.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sesong.combeenation.R;

public class LoadingActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Loading);
        setContentView(R.layout.activity_loading);

        /*preferences = getSharedPreferences("token", MODE_PRIVATE);
        String val = preferences.getString("token", null);
        Log.d("val data ", val);

        if (val != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);  // Intent 선언
                    startActivity(intent);   // Intent 시작
                    finish();
                }
            }, 2000);  // 로딩화면 시간
        }else{
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);  // Intent 선언
                    startActivity(intent);   // Intent 시작
                    finish();
                }
            }, 2000);  // 로딩화면 시간
        }*/
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);  // Intent 선언
                startActivity(intent);   // Intent 시작
                finish();
            }
        }, 2000);  // 로딩화면 시간
    }
}