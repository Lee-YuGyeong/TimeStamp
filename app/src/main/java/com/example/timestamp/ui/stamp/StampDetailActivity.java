package com.example.timestamp.ui.stamp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.DetailClickData;
import com.example.timestamp.DetailClickDataMy;
import com.example.timestamp.ImageDetailInfo;
import com.example.timestamp.ImageDetailResponseInfo;
import com.example.timestamp.ImageEditActivity;
import com.example.timestamp.OneImageDetailActivity;
import com.example.timestamp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StampDetailActivity extends AppCompatActivity {

    StampDetailAdapter adapter;

    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    String title;
    int num;

    Toolbar toolbar;

    int share;

    @Override
    public void onResume() {
        super.onResume();
        getMenuDetailList();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stamp_detail);

        title = getIntent().getStringExtra("title");
        num = getIntent().getIntExtra("num", 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("mine", Context.MODE_PRIVATE);
        share = sharedPreferences.getInt("share", -1);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new StampDetailAdapter();

        getMenuDetailList();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), OneImageDetailActivity.class);
                intent.putExtra("url", adapter.items.get(position).getImage());
                intent.putExtra("userID", adapter.items.get(position).getUserID());
                intent.putExtra("userName", adapter.items.get(position).getUserName());
                startActivity(intent);
            }
        });// 메뉴 그리드뷰


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stamp_detail_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.plusButton:
                dispatchTakePictureIntent();
                break;
            case R.id.infoButton:
                if(share == 0 ){
                    Intent intent = new Intent(getApplicationContext(), DetailClickDataMy.class);
                    intent.putExtra("title",title);
                    startActivity(intent);
                    break;
                }else{
                    Intent intent = new Intent(getApplicationContext(), DetailClickData.class);
                    intent.putExtra("title",title);
                    intent.putExtra("num",num);
                    startActivity(intent);
                    break;
                }

            case R.id.modifyButton:

                break;
            case R.id.deleteButton:

                break;

        }
        return super.onOptionsItemSelected(item);
    } //toolbar


    private void getMenuDetailList() {

        Api Api = APIClient.getClient().create(Api.class);
        Call<ImageDetailResponseInfo> call = Api.ImageGet(num);

        //finally performing the call
        call.enqueue(new Callback<ImageDetailResponseInfo>() {
            @Override
            public void onResponse(Call<ImageDetailResponseInfo> call, Response<ImageDetailResponseInfo> response) {

                if (response.isSuccessful()) {

                    ImageDetailResponseInfo imageDetailResponseInfo = response.body();
                    List<ImageDetailInfo> imageDetailInfoList = new ArrayList<ImageDetailInfo>(imageDetailResponseInfo.getImageDetailInfoList());

                    adapter.items.clear();


                    if (adapter.isEmpty() && imageDetailInfoList.size() != 0) {

                        for (int i = 0; i < imageDetailInfoList.size(); i++) {
                            adapter.addItem(new StampDetailGridItem(imageDetailInfoList.get(i).getImage(), imageDetailInfoList.get(i).getUserID(), imageDetailInfoList.get(i).getUserName()));
                        }

                    }

                    adapter.notifyDataSetChanged();

                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<ImageDetailResponseInfo> call, Throwable t) {

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

                            Intent intent1 = new Intent(getApplicationContext(), ImageEditActivity.class);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            intent1.putExtra("bitmap", byteArray);
                            intent1.putExtra("drawerName", title);
                            intent1.putExtra("myNum", num);

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

        ArrayList<StampDetailGridItem> items = new ArrayList<StampDetailGridItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(StampDetailGridItem item) {
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
            StampDetailGridItemView view = null;
            if (convertView == null) {
                view = new StampDetailGridItemView(getApplicationContext());
            } else {
                view = (StampDetailGridItemView) convertView;
            }

            StampDetailGridItem item = items.get(position);

            view.setImage(item.getImage());

            return view;

        }
    }

}
