package com.sesong.combeenation.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sesong.combeenation.Adapter.MyCursorAdapter;
import com.sesong.combeenation.R;

import java.io.File;

import static android.support.v4.content.FileProvider.getUriForFile;
import static java.security.AccessController.getContext;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, 7000);
        finish();
/*
        // 뷰
        GridView photoListView = (GridView) findViewById(R.id.photo_list);
        // 사진 데이터
        Cursor cursor = getContentResolver().query
                (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // From 절
                null,
                null,
                null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"); // Order By 절
        // 어댑터
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
        photoListView.setAdapter(adapter);
        // 클릭 이벤트 처리
        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 부분의 cursor 데이타
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                Toast.makeText(GalleryActivity.this, "사진 경로 : " + path, Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultPath", path);
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });*/
    }
}
