package com.example.timestamp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timestamp.login.LoginActivity;
import com.example.timestamp.ui.myStamp.MyStampDetailActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageEditActivity extends AppCompatActivity implements View.OnTouchListener, FragmentCallBack {

    TextView textView_date1;

    Bitmap bitmap;
    ImageView imageView;


    EditBorderFragment editBorderFragment;
    EditTimeFragment editTimeFragment;

    Date mDate;

    ImageView imageView_border1;

    String drawerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);


        // captureBitmapSave = (CaptureBitmapSave) findViewById(R.class.);
        final LinearLayout container = (LinearLayout) findViewById(R.id.capture_target_Layout);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView_border1 = (ImageView) findViewById(R.id.imageView_border1);

        byte[] arr = getIntent().getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        imageView.setImageBitmap(bitmap);

        drawerName = getIntent().getStringExtra("drawerName");

        long now = System.currentTimeMillis();
        mDate = new Date(now);

        TimeStyleButtonSelected("style", 1);
        setTab();

        textView_date1.setOnTouchListener(this);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap captureBitmap = setViewToBitmapImage(container);

                BitmapSave(captureBitmap);

                finish();

            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void BitmapSave(Bitmap bitmap) {

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", "null");

        File imageFile = null;
        try {
            imageFile = createFileFromBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("아아", "zz");
        try {
            Log.d("아아", drawerName);
            uploadFile(imageFile, userID, drawerName);
        } catch (URISyntaxException e) {
            Log.d("아아", " not try");
            e.printStackTrace();
        }
    }

    public File createFileFromBitmap(Bitmap bitmap) throws IOException {
        File newFile = new File(getFilesDir(), makeImageFileName());
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, fileOutputStream);
        fileOutputStream.close();
        return newFile;
    }


    public String makeImageFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        Date date = new Date();
        String strDate = simpleDateFormat.format(date);
        return strDate + ".png";
    }

    public void uploadFile(File file, String userID, String drawerName) throws URISyntaxException {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody drawerNameBody = RequestBody.create(MediaType.parse("text/plain"), drawerName);

        Call<List<MyResponse>> call = RetrofitClient.getInstance().getApi().uploadImage(requestFile, userIDBody, drawerNameBody);
        //finally performing the call
        call.enqueue(new Callback<List<MyResponse>>() {
            @Override
            public void onResponse(Call<List<MyResponse>> call, Response<List<MyResponse>> response) {
            }

            @Override
            public void onFailure(Call<List<MyResponse>> call, Throwable t) {
            }
        });
    } //retrofit2 사진 업로드


    public void setTab() {

        final FragmentManager fragmentManager = getSupportFragmentManager();

        editTimeFragment = new EditTimeFragment();
        fragmentManager.beginTransaction().add(R.id.editContainer, editTimeFragment).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Time"));
        tabs.addTab(tabs.newTab().setText("테두리"));


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (position == 0) {

                    if (editTimeFragment == null) {
                        editTimeFragment = new EditTimeFragment();
                        fragmentManager.beginTransaction().add(R.id.editContainer, editTimeFragment).commit();
                    }

                    if (editTimeFragment != null)
                        fragmentManager.beginTransaction().show(editTimeFragment).commit();
                    fragmentManager.beginTransaction().hide(editBorderFragment).commit();

                } else if (position == 1) {

                    if (editBorderFragment == null) {
                        editBorderFragment = new EditBorderFragment();
                        fragmentManager.beginTransaction().add(R.id.editContainer, editBorderFragment).commit();
                    }

                    fragmentManager.beginTransaction().hide(editTimeFragment).commit();
                    if (editBorderFragment != null)
                        fragmentManager.beginTransaction().show(editBorderFragment).commit();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    public void BorderButtonSelected(String command, boolean state) {

        if (state == true) {
            imageView_border1.setVisibility(View.VISIBLE);
        } else {
            imageView_border1.setVisibility(View.INVISIBLE);
        }

    }

    public void TimeStyleButtonSelected(String command, int data) {

        SimpleDateFormat simpleDate;
        textView_date1 = (TextView) findViewById(R.id.textView_date1);


        if (data == 1) {
            simpleDate = new SimpleDateFormat("");
            textView_date1.setTextSize(0);
        } else if (data == 2) {
            simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) \na h:mm");
            textView_date1.setTextSize(25);
        } else if (data == 3) {
            simpleDate = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
            textView_date1.setTextSize(40);
        } else {
            simpleDate = new SimpleDateFormat("d MMM, hh:mm a ", Locale.ENGLISH);
            textView_date1.setTextSize(25);
        }

        String getTime = simpleDate.format(mDate);


        textView_date1.setText(getTime);


    } //글자 스타일 적용

    public void TimeColorButtonSelected(String command, int data, String key) {

        if (key == "time") {
            textView_date1.setTextColor(data);
        } else {

            GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.border);
            drawable.setStroke(10, data);
            imageView_border1.setImageDrawable(drawable);


        }
    } // 글자 색 적용

    public void TimeFontButtonSelected(String command, int font) {

        if (font == 1) {
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.nanumbarunpen);
            textView_date1.setTypeface(typeface);
        } else if (font == 2) {
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.nanumsquare);
            textView_date1.setTypeface(typeface);
        } else if (font == 3) {
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.maruburi);
            textView_date1.setTypeface(typeface);
        } else if (font == 4) {
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.bmhanna);
            textView_date1.setTypeface(typeface);
        }

    } // 글자 폰트 적용

    public static Bitmap setViewToBitmapImage(View view) {
//Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    float oldXvalue;
    float oldYvalue;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue = event.getX();
            oldYvalue = event.getY();
            //  Log.i("Tag1", "Action Down X" + event.getX() + "," + event.getY());
            Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            v.setX(event.getRawX() - oldXvalue);
            v.setY(event.getRawY() - (oldYvalue + v.getHeight()));
            //  Log.i("Tag2", "Action Down " + me.getRawX() + "," + me.getRawY());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            if (v.getX() > width && v.getY() > height) {
                v.setX(width);
                v.setY(height);
            } else if (v.getX() < 0 && v.getY() > height) {
                v.setX(0);
                v.setY(height);
            } else if (v.getX() > width && v.getY() < 0) {
                v.setX(width);
                v.setY(0);
            } else if (v.getX() < 0 && v.getY() < 0) {
                v.setX(0);
                v.setY(0);
            } else if (v.getX() < 0 || v.getX() > width) {
                if (v.getX() < 0) {
                    v.setX(0);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                } else {
                    v.setX(width);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                }
            } else if (v.getY() < 0 || v.getY() > height) {
                if (v.getY() < 0) {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(0);
                } else {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(height);
                }
            }


        }
        return true;
    }

}
