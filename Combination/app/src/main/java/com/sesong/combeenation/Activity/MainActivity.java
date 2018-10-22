package com.sesong.combeenation.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.JsonObject;
import com.sesong.combeenation.Adapter.TabPagerAdapter;
import com.sesong.combeenation.R;
import com.sesong.combeenation.retrofit.RetrofitService;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String TAG = "TAG";
    private Toolbar myToolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    String token;

    private final int GALLERY_CODE = 1112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        viewPager = findViewById(R.id.pager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialogcustom, (ViewGroup) findViewById(R.id.layout_root));

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        Log.d("Main token ", token);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(layout);
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText title = layout.findViewById(R.id.menu_name);
                        EditText material1 = layout.findViewById(R.id.material1);
                        EditText material2 = layout.findViewById(R.id.material2);
                        EditText material3 = layout.findViewById(R.id.material3);
                        Button imageSearchBtn = layout.findViewById(R.id.search_image_button);
                        RadioGroup radioGroup = layout.findViewById(R.id.radio_group);
                        String contentType = null;
                        int checkedId = radioGroup.getCheckedRadioButtonId();
                        switch (checkedId) {
                            case R.id.radio_food:
                                contentType = "food";
                                break;
                            case R.id.radio_beauty:
                                contentType = "beauty";
                                break;
                            case R.id.radio_travel:
                                contentType = "trip";
                                break;
                        }
                        Log.d("contentType ", contentType);
                        String menuTitle = title.getText().toString();
                        String totalMaterial = material1.getText().toString() + " + " +
                                material2.getText().toString() + " + " +
                                material3.getText().toString();
                        imageSearchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent galleryIntent = new Intent(MainActivity.this, GalleryActivity.class);
                                startActivity(galleryIntent);
                            }
                        });
                        Log.d("menuTitle : ", menuTitle);
                        Log.d("totalMaterial : ", totalMaterial);
                        //addMenu(menuTitle, totalMaterial, file, contentType);
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.create().show();
            }
        });
    }
/*

    public void addMenu(String name, String combination, File image, String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        final Call<JsonObject> response = retrofitService.combinations(token, name, image, combination, type);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("Response (body)", String.valueOf(response.body()));
                Log.d("Response (code)", String.valueOf(response.code()));
                Log.d("Response (message)", String.valueOf(response.message()));
                Log.d("Response (isSuccessful)", String.valueOf(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("addMenu Failed!!!", String.valueOf(response));
            }
        });
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mypage:
                Intent intent = new Intent(this, MypageActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 서치뷰의 내용으로 검색을 수행할 때 호출됨
                Log.d(TAG, "onQueryTextSubmit: " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 서치뷰의 글자가 변경될 때마다 호출됨
                Log.d("MainActivity", "onQueryTextChange: " + newText);
                return true;
            }
        });
        return true;
    }
}
