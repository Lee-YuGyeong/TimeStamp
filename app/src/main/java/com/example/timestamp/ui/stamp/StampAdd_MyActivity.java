package com.example.timestamp.ui.stamp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.R;
import com.example.timestamp.StampTitleImageActivity;
import com.example.timestamp.SuccessResponseInfo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StampAdd_MyActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    int resId;
    String title;


    String userID;

    Uri selectedImage;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_add_my);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("메뉴 추가");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);


        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");


        Button titleButton = (Button) findViewById(R.id.titleButton);
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StampTitleImageActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Button titleButton_g = (Button) findViewById(R.id.titleButton_g);
        titleButton_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/-");
                startActivityForResult(intent, 1);
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = editText.getText().toString();
                uploadMenu(selectedImage, userID, title, 0);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    public void uploadMenu(Uri fileUri, String userID, String drawerTitle, int share) {


        File file = new File(getRealPathFromURI(fileUri)); //절대경로로 바꾸기

        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody drawerTitleBody = RequestBody.create(MediaType.parse("text/plain"), drawerTitle);


        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.MenuUpload(requestFile, userIDBody, drawerTitleBody, share);
        //finally performing the call
        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, Response<SuccessResponseInfo> response) {
                if (response.isSuccessful()) {
                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {

            }
        });

    }

    private String getRealPathFromURI(Uri contentURI) {

        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();

        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;

    } // 절대경로로 변환


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Bitmap bm;
        if (requestCode == 0) { //제공된 사진 선택
            if (intent != null) {
                resId = intent.getIntExtra("resId", 0);
                imageView.setImageResource(resId);
            }
        } else if (requestCode == 1) { //갤러리 사진 선택
            if (intent != null) {
                //              try {

                selectedImage = intent.getData();
                imageView.setImageURI(selectedImage);

//                    bm = MediaStore.Images.Media.getBitmap(getContentResolver(), intent.getData());
//                    Bitmap bitmap = rotateImage(intent.getData(), bm);
//
//                    if (bitmap.getHeight() >= bitmap.getWidth()) {
//                        Bitmap bitmap1 = resizeBitmap(bitmap, bitmap.getWidth(), bitmap.getWidth());
//                        Bitmap bitmap2 = cropBitmap(bitmap1, bitmap1.getWidth(), bitmap1.getWidth());
//                        selectedBitmap = bitmap2;
//                        imageView.setImageBitmap(bitmap2);
//
//                    } else {
//                        Bitmap bitmap1 = resizeBitmap(bitmap, bitmap.getHeight(), bitmap.getHeight());
//                        Bitmap bitmap2 = cropBitmap(bitmap1, bitmap1.getHeight(), bitmap1.getHeight());
//                        selectedBitmap = bitmap2;
//                        imageView.setImageBitmap(bitmap2);
//
//                    }
//
//
//                } catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (OutOfMemoryError e) {
//                    Toast.makeText(getApplicationContext(), "이미지 용량이 너무 큽니다.", Toast.LENGTH_SHORT).show();
//                } //비트맵 조절


            }

        }

    }


//    public void BitmapSave(Bitmap bitmap, String title) {
//
//        File imageFile = null;
//        try {
//            imageFile = createFileFromBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            //uploadMenu(imageFile, userID, title);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }


//    public File createFileFromBitmap(Bitmap bitmap) throws IOException {
//        File newFile = new File(getFilesDir(), makeImageFileName());
//        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//        bitmap.compress(Bitmap.CompressFormat.PNG, 60, fileOutputStream);
//        fileOutputStream.close();
//        return newFile;
//    }//비트맵 -> 파일 변환

//
//    public String makeImageFileName() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
//        Date date = new Date();
//        String strDate = simpleDateFormat.format(date);
//        return strDate + ".png";
//    } //파일 이름 만들기

//    public Bitmap rotateImage(Uri uri, Bitmap bitmap) throws IOException {
//        InputStream in = getContentResolver().openInputStream(uri);
//        ExifInterface exifInterface = new ExifInterface(in);
//        in.close();
//
//        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//        Matrix matrix = new Matrix();
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
//            matrix.postRotate(90);
//        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
//            matrix.postRotate(180);
//        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
//            matrix.postRotate(270);
//        }
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//    } 비트맵 회전

//    public Bitmap cropBitmap(Bitmap bitmap, int width, int height) {
//        int originWidth = bitmap.getWidth();
//        int originHeight = bitmap.getHeight();
//
//        // 이미지를 crop 할 좌상단 좌표
//        int x = 0;
//        int y = 0;
//
//        if (originWidth > width) { // 이미지의 가로가 view 의 가로보다 크면..
//            x = (originWidth - width) / 2;
//        }
//
//        if (originHeight > height) { // 이미지의 세로가 view 의 세로보다 크면..
//            y = (originHeight - height) / 2;
//        }
//
//        Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
//        return cropedBitmap;
//    }//이미지 자르기
//
//    public Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
//        if (bitmap.getWidth() != width || bitmap.getHeight() != height) {
//            float ratio = 1.0f;
//
//            if (width > height) {
//                ratio = (float) width / (float) bitmap.getWidth();
//            } else {
//                ratio = (float) height / (float) bitmap.getHeight();
//            }
//
//            bitmap = Bitmap.createScaledBitmap(bitmap,
//                    (int) (((float) bitmap.getWidth()) * ratio), // Width
//                    (int) (((float) bitmap.getHeight()) * ratio), // Height
//                    false);
//        }
//
//        return bitmap;
//    }//이미지 사이즈 조절


}
