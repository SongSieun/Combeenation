package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.sesong.combeenation.R;

public class AddMenuActivity extends AppCompatActivity {
    private EditText title, content1, content2, content3, content4;
    private Button searchImage, returnBtn, okBtn;
    private RadioGroup radioGroup;
    private String menuTitle, menuContent, menuType, imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);

        title = findViewById(R.id.menu_name);
        content1 = findViewById(R.id.material1);
        content2 = findViewById(R.id.material2);
        content3 = findViewById(R.id.material3);
        content4 = findViewById(R.id.material4);
        radioGroup = findViewById(R.id.radio_group);
        searchImage = findViewById(R.id.search_image_button);
        returnBtn = findViewById(R.id.returnbtn);
        okBtn = findViewById(R.id.okbtn);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivityForResult(intent, 3000);*/
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, 7000);
            }
        });
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuTitle = title.getText().toString();
                menuContent = content1.getText().toString() + " + " +
                        content2.getText().toString() + " + " +
                        content3.getText().toString() + " + " +
                        content4.getText().toString();

                Intent resultAddIntent = new Intent();
                resultAddIntent.putExtra("menuTitle", menuTitle);
                resultAddIntent.putExtra("menuContent", menuContent);
                resultAddIntent.putExtra("menuType", menuType);
                resultAddIntent.putExtra("imagePath", imagePath);
                setResult(RESULT_OK, resultAddIntent);
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_food:
                        menuType = "food";
                        break;
                    case R.id.radio_beauty:
                        menuType = "beauty";
                        break;
                    case R.id.radio_travel:
                        menuType = "trip";
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 7000:
                    //imagePath = data.getStringExtra("resultPath");

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.d("imagePath ", imagePath);
                    break;
            }
        }
    }
}
