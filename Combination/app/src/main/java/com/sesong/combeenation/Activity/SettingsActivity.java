package com.sesong.combeenation.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sesong.combeenation.Fragment.SettingsFragment;
import com.sesong.combeenation.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
