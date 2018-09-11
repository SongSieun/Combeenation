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
import android.widget.EditText;

import com.sesong.combeenation.Adapter.TabPagerAdapter;
import com.sesong.combeenation.R;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialogcustom, (ViewGroup) findViewById(R.id.layout_root));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(layout);
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText title = (EditText) layout.findViewById(R.id.menu_name);
                        EditText material1 = (EditText) layout.findViewById(R.id.material1);
                        EditText material2 = (EditText) layout.findViewById(R.id.material2);
                        EditText material3 = (EditText) layout.findViewById(R.id.material3);
                        EditText material4 = (EditText) layout.findViewById(R.id.material4);
                        EditText material5 = (EditText) layout.findViewById(R.id.material5);
                        EditText editUrlPic = (EditText) layout.findViewById(R.id.pic_url);
                        String MenuTitle = title.getText().toString();
                        String totalMaterial = material1.getText().toString() + " + " +
                                material2.getText().toString() + " + " +
                                material3.getText().toString() + " + " +
                                material4.getText().toString() + " + " +
                                material5.getText().toString();
                        String UrlPic = editUrlPic.getText().toString();
                        Log.d("MenuTitle : ", MenuTitle);
                        Log.d("totalMaterial : ", totalMaterial);
                        Log.d("UrlPic", UrlPic);
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.create().show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mypage:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
