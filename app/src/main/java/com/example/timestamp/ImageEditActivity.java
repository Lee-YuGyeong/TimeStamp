package com.example.timestamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timestamp.ui.myStamp.MyStampDetailActivity;
import com.example.timestamp.ui.myStamp.MyStampDetailGridItem;
import com.google.android.material.tabs.TabLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageEditActivity extends AppCompatActivity implements View.OnTouchListener, FragmentCallBack {

    TextView textView_date1;
//    TextView textView_date2;
//    TextView textView_date3;
//    TextView textView_date4;
//    TextView textView_date5;

    boolean visibility1 = false;
//    boolean visibility2 = false;
//    boolean visibility3 = false;
//    boolean visibility4 = false;
//    boolean visibility5 = false;

    Bitmap bitmap;

    EditBorderFragment editBorderFragment;
    EditTimeFragment editTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);



        final LinearLayout container = (LinearLayout) findViewById(R.id.capture_target_Layout);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        byte[] arr = getIntent().getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        imageView.setImageBitmap(bitmap);

        setTime();
        setTab();

        textView_date1.setOnTouchListener(this);
        //   goToFragment(bitmap, fragmentImageEditOrigin, 0);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap captureBitmap = setViewToBitmapImage(container);
//                ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
//                imageView2.setImageBitmap(captureBitmap);

                Intent intent = new Intent(getApplicationContext(), MyStampDetailActivity.class);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                captureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
//                intent.putExtra("captureBitmap",byteArray);
                startActivity(intent); //비트맵 서버 저장하기

            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



//        Button button1 = (Button) findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean bool = visibility(textView_date1,visibility1);
//                visibility1 = bool;
//
//            }
//        });////////////


//        Button button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean bool = visibility(textView_date2,visibility2);
//                visibility2 = bool;
//            }
//        });
//
//        Button button3 = (Button) findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean bool = visibility(textView_date3,visibility3);
//                visibility3 = bool;
//            }
//        });
//
//        Button button4 = (Button) findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean bool = visibility(textView_date4,visibility4);
//                visibility4 = bool;
//            }
//        });
//
//
//        Button button5 = (Button) findViewById(R.id.button5);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean bool = visibility(textView_date5,visibility5);
//                visibility5 = bool;
//            }
//        });

        boolean bool = visibility(textView_date1, visibility1);
        visibility1 = bool;
    }

    public void setTab(){
        editBorderFragment = new EditBorderFragment();
        editTimeFragment = new EditTimeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.editContainer,editTimeFragment).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Time"));
        tabs.addTab(tabs.newTab().setText("테두리"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if ( position == 0 ){
                    selected = editTimeFragment;
                }else if ( position ==1){
                    selected = editBorderFragment;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.editContainer,selected).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void TimeStyleButtonSelected(String command, int data) {

        if (data == 1) {
            boolean bool = visibility(textView_date1, visibility1);
            visibility1 = bool;
        }

    } //글자 스타일 적용

    public void TimeColorButtonSelected(String command, String data) {

        textView_date1.setTextColor(Color.parseColor(data));

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

    public boolean visibility(TextView textView, boolean visibility) {

        if (visibility == false) {
            textView.setVisibility(View.VISIBLE);
            return true;
        } else {
            textView.setVisibility(View.INVISIBLE);
            return false;
        }

    }

    public void setTime() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) \na h:mm:ss");
        String getTime = simpleDate.format(mDate);

        textView_date1 = (TextView) findViewById(R.id.textView_date1);
        textView_date1.setText(getTime);

//        textView_date2 = (TextView) findViewById(R.id.textView_date2);
//        textView_date2.setText(getTime);
//
//        textView_date3 = (TextView) findViewById(R.id.textView_date3);
//        textView_date3.setText(getTime);
//
//        textView_date4 = (TextView) findViewById(R.id.textView_date4);
//        textView_date4.setText(getTime);
//
//        textView_date5 = (TextView) findViewById(R.id.textView_date5);
//        textView_date5.setText(getTime);
    }

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
