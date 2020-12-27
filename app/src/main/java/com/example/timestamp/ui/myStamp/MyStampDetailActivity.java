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
import com.example.timestamp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyStampDetailActivity extends AppCompatActivity {

    StampDetailAdapter adapter;

    final String TAG = getClass().getSimpleName();
    ImageView imageView;
    String currentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    String title;

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

        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new StampDetailAdapter();


        ////////// 비트맵으로 변경
        Context context = getApplicationContext();
        Drawable drawable = getResources().getDrawable(R.drawable.background1);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        Drawable drawable2 = getResources().getDrawable(R.drawable.background2);
        Bitmap bitmap2 = ((BitmapDrawable)drawable2).getBitmap();

        adapter.addItem(new MyStampDetailGridItem(bitmap));
        adapter.addItem(new MyStampDetailGridItem(bitmap2));

        ////////////////////////////

        gridView.setAdapter(adapter);

        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dispatchTakePictureIntent();

            }
        });

        ReceiveImageFromEditActivity();

    }




    public void ReceiveImageFromEditActivity() {


//        if (getIntent().getByteArrayExtra("captureBitmap") != null) {

//
//            byte[] arr = getIntent().getByteArrayExtra("captureBitmap");
//            Bitmap bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
//            adapter.addItem(new MyStampDetailGridItem(bitmap));
//            adapter.notifyDataSetChanged();

//
//        }
        //서버에 저장한거 그리드뷰 보이기
    }


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

                        if (bitmap != null) {
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


                            //            DrawToBitmap drawToBitmap = new DrawToBitmap();
                            //           Bitmap bmp =drawToBitmap.drawTimeToBitmap(this,rotatedBitmap);

//                            long now = System.currentTimeMillis();
//                            Date mDate = new Date(now);
//
//                            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) \na h:mm:ss");
//                            String getTime = simpleDate.format(mDate);
//
//                            adapter.addItem(new MyStampDetailGridItem(rotatedBitmap, getTime));
//                            adapter.notifyDataSetChanged();

                            Intent intent1 = new Intent(getApplicationContext(), ImageEditActivity.class);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            intent1.putExtra("bitmap", byteArray);
                            intent1.putExtra("drawerName",title);
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
//
//    public static void saveBitmaptoJpeg(Bitmap bitmap, String folder, String name) {
//        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String foler_name = "/" + folder + "/";
//        String file_name = name + ".jpg";
//        String string_path = ex_storage + foler_name;
//        File file_path;
//        try {
//            file_path = new File(string_path);
//            if (!file_path.isDirectory()) {
//                file_path.mkdirs();
//            }
//            FileOutputStream out = new FileOutputStream(string_path + file_name);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//            out.close();
//        } catch (FileNotFoundException exception) {
//            Log.e("FileNotFoundException", exception.getMessage());
//        } catch (IOException exception) {
//            Log.e("IOException", exception.getMessage());
//        }
//    }


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
