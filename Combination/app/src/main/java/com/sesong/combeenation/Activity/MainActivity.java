package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.JsonObject;
import com.sesong.combeenation.Adapter.TabPagerAdapter;
import com.sesong.combeenation.Fragment.FoodFragment;
import com.sesong.combeenation.R;
import com.sesong.combeenation.TokenData;
import com.sesong.combeenation.databinding.ActivityMainBinding;
import com.sesong.combeenation.retrofit.RetrofitService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String TAG = "TAG";
    private String menuTitle, menuContent, menuType, token, imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.myToolbar);

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);

        binding.tabLayout.setupWithViewPager(binding.viewPager);

        token = TokenData.getInstance().getToken();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddMenu = new Intent(MainActivity.this, AddMenuActivity.class);
                startActivityForResult(goAddMenu, 2000);
            }
        });
    }

    public void addMenu(String name, String image, String combination, String type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        final Call<JsonObject> response = retrofitService.combinations(token, name, image, combination, type);
        response.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("MAResponse (body)", String.valueOf(response.body()));
                Log.d("MAResponse (code)", String.valueOf(response.code()));
                Log.d("MAResponse (message)", String.valueOf(response.message()));
                Log.d("MAResponse (isSucce)", String.valueOf(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("addMenu Failed!!!", String.valueOf(response));
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2000:
                    menuTitle = data.getStringExtra("menuTitle");
                    menuContent = data.getStringExtra("menuContent");
                    menuType = data.getStringExtra("menuType");
                    imageString = data.getStringExtra("imageString");
                    Log.d("menuTitleMaIn ", menuTitle);
                    Log.d("menuContentMaIn ", menuContent);
                    Log.d("menuTypeMaIn ", menuType);
                    Log.d("imagePathMaIn ", imageString);
                    addMenu(menuTitle, imageString, menuContent, menuType);

                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mypage:
                Intent intent = new Intent(this, MypageActivity.class);
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
