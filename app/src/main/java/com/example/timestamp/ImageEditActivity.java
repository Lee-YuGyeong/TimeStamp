package com.example.timestamp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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

import com.example.timestamp.ui.myStamp.MyStampDetailActivity;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageEditActivity extends AppCompatActivity implements View.OnTouchListener, FragmentCallBack {

    TextView textView_date1;
    // TextView textView_date2;
//    TextView textView_date3;
//    TextView textView_date4;
//    TextView textView_date5;

    boolean visibility1 = false;
//    boolean visibility2 = false;
//    boolean visibility3 = false;
//    boolean visibility4 = false;
//    boolean visibility5 = false;

    Bitmap bitmap;
    ImageView imageView;

    EditBorderFragment editBorderFragment;
    EditTimeFragment editTimeFragment;
    EditTextActivity editTextFragment;

    Date mDate;

    ImageView imageView_border1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        final LinearLayout container = (LinearLayout) findViewById(R.id.capture_target_Layout);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView_border1 = (ImageView) findViewById(R.id.imageView_border1);

        byte[] arr = getIntent().getByteArrayExtra("bitmap");
        bitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        imageView.setImageBitmap(bitmap);


        long now = System.currentTimeMillis();
        mDate = new Date(now);

        TimeStyleButtonSelected("style", 1);
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

//        boolean bool = visibility(textView_date1, visibility1);
//        visibility1 = bool;
    }


    public void setTab() {
        editBorderFragment = new EditBorderFragment();
        editTimeFragment = new EditTimeFragment();
        editTextFragment = new EditTextActivity();

        getSupportFragmentManager().beginTransaction().add(R.id.editContainer, editTimeFragment).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Time"));
        tabs.addTab(tabs.newTab().setText("테두리"));
        tabs.addTab(tabs.newTab().setText("글"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = editTimeFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.editContainer, selected).commit();
                } else if (position == 1) {
                    selected = editBorderFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.editContainer, selected).commit();

                } else {
                    Intent intent = new Intent(getApplicationContext(), EditTextActivity.class);
                    startActivityForResult(intent, 1000);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                TextView inputText = findViewById(R.id.inputText);

                    String text = data.getStringExtra("text");
                    inputText.setText(text);
            }
        }
    }

    public void BorderSizeButtonSelected(String command, int data) {

        if (data == 1) {
            boolean bool = visibility(imageView_border1, visibility1);
            visibility1 = bool;
        }
    }

    public void TimeStyleButtonSelected(String command, int data) {

        SimpleDateFormat simpleDate;
        textView_date1 = (TextView) findViewById(R.id.textView_date1);


        if (data == 1) {
            simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E) \na h:mm");
            textView_date1.setTextSize(25);
        } else if (data == 2) {
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

            final GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.border);
            drawable.setStroke(10, data);
            imageView_border1.setImageDrawable(drawable);

            Log.d("아아",String.valueOf(drawable.getColor()));

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

    public boolean visibility(ImageView imageView, boolean visibility) {

        if (visibility == false) {
            imageView.setVisibility(View.VISIBLE);
            return true;
        } else {
            imageView.setVisibility(View.INVISIBLE);
            return false;
        }

    }

    public void setTime(int i) {


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
