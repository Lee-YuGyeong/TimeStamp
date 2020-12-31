package com.example.timestamp.ui.myStamp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.timestamp.ImageEditActivity;
import com.example.timestamp.MenuDetailInfo;
import com.example.timestamp.MenuDetailResponseInfo;
import com.example.timestamp.MyMenuInfo;
import com.example.timestamp.MyResponse;
import com.example.timestamp.R;
import com.example.timestamp.ResponseInfo;
import com.example.timestamp.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStampDetailActivity extends AppCompatActivity {

    StampDetailAdapter adapter;

    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    String title;
    int myNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stamp_detail);

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = getIntent().getStringExtra("title");
        TextView textView = (TextView) findViewById(R.id.toolBar_textView);
        textView.setText(title);

        myNum = getIntent().getIntExtra("myNum",0);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new StampDetailAdapter();

     //   getMenuDetailList();

//        ////////// 비트맵으로 변경
//        Context context = getApplicationContext();
//        Drawable drawable = getResources().getDrawable(R.drawable.background1);
//        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
//
//        Drawable drawable2 = getResources().getDrawable(R.drawable.background2);
//        Bitmap bitmap2 = ((BitmapDrawable)drawable2).getBitmap();
//
//        adapter.addItem(new MyStampDetailGridItem(bitmap));
//        adapter.addItem(new MyStampDetailGridItem(bitmap2));
//
//        ////////////////////////////

        getMenuDetailList();
        gridView.setAdapter(adapter);

        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("아아","0000");
                 dispatchTakePictureIntent();
                Log.d("아아","0");
            }
        });


    }

    private void getMenuDetailList() {

        Log.d("아아","0121");

        Call<MenuDetailResponseInfo> call = RetrofitClient.getInstance().getApi().MyImageGet(myNum);

        //finally performing the call
        call.enqueue(new Callback<MenuDetailResponseInfo>() {
            @Override
            public void onResponse(Call<MenuDetailResponseInfo> call, Response<MenuDetailResponseInfo> response) {

                if (response.isSuccessful()) {
                    MenuDetailResponseInfo menuDetailResponseInfo = response.body();
                    List<MenuDetailInfo> menuDetailInfoList = new ArrayList<MenuDetailInfo>(menuDetailResponseInfo.getMenuDetailInfoList());

                    adapter.items.clear();

                    Log.d("아아","11");

                    if (adapter.isEmpty()&& menuDetailInfoList.size()!=0 ) {

                        for (int i = 0; i < menuDetailInfoList.size(); i++) {
                            adapter.addItem(new MyStampDetailGridItem(menuDetailInfoList.get(i).getImage()));
                            Log.d("아아",i +":   " +menuDetailInfoList.get(i).getImage()+"");
                        }
                        adapter.notifyDataSetChanged();


                        Log.d("아아", "성공 받아오기 :" +menuDetailInfoList.get(0).getImage());
                    }


                } else { //response 실패
                    Log.d("아아", "실패1 받아오기 : ");
                }

            }

            @Override
            public void onFailure(Call<MenuDetailResponseInfo> call, Throwable t) {
                Log.d("아아", "실패2 받아오기 ggggg: " + t.getMessage());
            }
        });
    } // retrofit 데이터 받아오기


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO: {
                    if (resultCode == RESULT_OK) {

                        File file = new File(currentPhotoPath);
                        Bitmap bitmap = MediaStore.Images.Media
                                .getBitmap(getContentResolver(), Uri.fromFile(file)); //원본

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;
                        Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath, options); //줄이기

                        if (imageBitmap != null) {
                            ExifInterface ei = new ExifInterface(currentPhotoPath);
                            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_UNDEFINED);

                            Bitmap rotatedBitmap = null;
                            switch (orientation) {

                                case ExifInterface.ORIENTATION_ROTATE_90:
                                    rotatedBitmap = rotateImage(imageBitmap, 90);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_180:
                                    rotatedBitmap = rotateImage(imageBitmap, 180);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_270:
                                    rotatedBitmap = rotateImage(imageBitmap, 270);
                                    break;

                                case ExifInterface.ORIENTATION_NORMAL:
                                default:
                                    rotatedBitmap = imageBitmap;
                            }

                            Log.d("아아","1222");
                            Intent intent1 = new Intent(getApplicationContext(), ImageEditActivity.class);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            intent1.putExtra("bitmap", byteArray);
                            intent1.putExtra("drawerName",title);
                            intent1.putExtra("myNum",myNum);

                            Log.d("아아","1");
                            startActivity(intent1);


                        }
                    }
                    break;
                }
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }


    class StampDetailAdapter extends BaseAdapter {

        ArrayList<MyStampDetailGridItem> items = new ArrayList<MyStampDetailGridItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MyStampDetailGridItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyStampDetailGridItemView view = null;
            if (convertView == null) {
                view = new MyStampDetailGridItemView(getApplicationContext());
            } else {
                view = (MyStampDetailGridItemView) convertView;
            }

            MyStampDetailGridItem item = items.get(position);

            view.setImage(item.getImage());

            return view;

        }
    }

}
