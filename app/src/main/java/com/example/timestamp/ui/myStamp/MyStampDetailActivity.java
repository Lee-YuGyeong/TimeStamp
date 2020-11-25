package com.example.timestamp.ui.myStamp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.timestamp.R;

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

        GridView gridView = (GridView) findViewById(R.id.gridView);

        adapter = new StampDetailAdapter();

//        adapter.addItem(new MyStampDetailGridItem(R.drawable.background1));
//        adapter.addItem(new MyStampDetailGridItem(R.drawable.background2));

        gridView.setAdapter(adapter);

        Button plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

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

                            long now = System.currentTimeMillis();
                            Date mDate = new Date(now);

                            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) \na h:mm:ss");
                            String getTime = simpleDate.format(mDate);

                            adapter.addItem(new MyStampDetailGridItem(rotatedBitmap,getTime));
                            adapter.notifyDataSetChanged();

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

//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(currentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

//    private void setPic() {
//        // Get the dimensions of the View
//        int targetW = imageView.getWidth();
//        int targetH = imageView.getHeight();
//
//        // Get the dimensions of the bitmap
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//
//        // Determine how much to scale down the image
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
//
//        // Decode the image file into a Bitmap sized to fill the View
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
//        imageView.setImageBitmap(bitmap);
//    }


//    public Bitmap drawTextToBitmap(Context mContext, Bitmap bitmap) {
//
//        long now = System.currentTimeMillis();
//        Date mDate = new Date(now);
//
//        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) a hh:mm:ss");
//        String getTime = simpleDate.format(mDate);
//
//
//        try {
//            Resources resources = mContext.getResources();
//            float scale = resources.getDisplayMetrics().density;
//           // Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);
//            android.graphics.Bitmap.Config bitmapConfig =   bitmap.getConfig();
//            // set default bitmap config if none
//            if(bitmapConfig == null) {
//                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
//            }
//            // resource bitmaps are imutable,
//            // so we need to convert it to mutable one
//            bitmap = bitmap.copy(bitmapConfig, true);
//
//            Canvas canvas = new Canvas(bitmap);
//            // new antialised Paint
//            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//            // text color -
//            paint.setColor(Color.rgb(255,255, 255));
//            // text size in pixels
//            paint.setTextSize((int) (20 * scale));
//            // text shadow
//            paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);
//
//            paint.setFakeBoldText(true);
//
//            // draw text to the Canvas center
//            Rect bounds = new Rect();
//            paint.getTextBounds(getTime, 0, getTime.length(), bounds);
//            int x = (bitmap.getWidth() - bounds.width())/6;
//            int y = (bitmap.getHeight() + bounds.height())/5;
//
//            canvas.drawText(getTime, x * scale, y * scale, paint);
//
//            return bitmap;
//        } catch (Exception e) {
//            // TODO: handle exception
//
//            return null;
//        }
//
//    } //이미지위에 글자적기

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
            view.setText(item.getDate());

            return view;

        }
    }

}
