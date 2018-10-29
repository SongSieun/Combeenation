package com.sesong.combeenation.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import com.sesong.combeenation.R;
import com.sesong.combeenation.databinding.ActivityAddmenuBinding;

import java.io.ByteArrayOutputStream;
import static android.graphics.Bitmap.*;

public class AddMenuActivity extends AppCompatActivity {
    private String menuTitle, menuContent, menuType, imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityAddmenuBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_addmenu);

        binding.searchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                Log.d("AddMenuIntent  ", String.valueOf(intent));
                startActivityForResult(intent, 7000);
            }
        });
        binding.returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuTitle = binding.menuTitle.getText().toString();
                menuContent = binding.material1.getText().toString() + " + " +
                        binding.material2.getText().toString() + " + " +
                        binding.material3.getText().toString() + " + " +
                        binding.material4.getText().toString();

                Intent resultAddIntent = new Intent();
                resultAddIntent.putExtra("menuTitle", menuTitle);
                resultAddIntent.putExtra("menuContent", menuContent);
                resultAddIntent.putExtra("menuType", menuType);
                resultAddIntent.putExtra("imageString", imageString);
                setResult(RESULT_OK, resultAddIntent);
                finish();
            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                    String finalImageString = sendPicture(data.getData()); //갤러리에서 가져오기
                    Log.d("AddMenuIntentData  ", finalImageString);
                    break;
            }
        }
    }

    private String sendPicture(Uri imgUri) {
        String imagePath = getRealPathFromURI(imgUri); // path 경로
        Log.d("AddMenuRealPath  ", imagePath);
        //경로를 통해 비트맵으로 전환
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        Log.d("AddMenuBitmap  ", String.valueOf(bitmap));
        imageString = getStringFromBitmap(bitmap);
        Log.d("AddMenuLog  ", imageString);

        return imageString;
    }

    // 비트맵 -> 스트링
    private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    private Bitmap getBitmapFromString(String jsonString) {
        byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree) {
        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }
}
