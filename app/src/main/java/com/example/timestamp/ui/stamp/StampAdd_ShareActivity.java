package com.example.timestamp.ui.stamp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timestamp.R;
import com.example.timestamp.StampAddMenuImageFragment;
import com.example.timestamp.StampAddMenuTagFragment;
import com.example.timestamp.StampAddMenuTitleFragment;


public class StampAdd_ShareActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_stamp_add_share);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("스탬프방 추가");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.room_container, new StampAddMenuTitleFragment()).commit();
//        editText = (EditText) findViewById(R.id.editText);
//        imageView = (ImageView) findViewById(R.id.imageView);
//
//
//        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
//        userID = sharedPreferences.getString("userID", "null");
//
//
//        Button titleButton = (Button) findViewById(R.id.titleButton);
//        titleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), StampTitleImageActivity.class);
//                startActivityForResult(intent, 0);
//            }
//        });
//
//        Button titleButton_g = (Button) findViewById(R.id.titleButton_g);
//        titleButton_g.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/-");
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        Button saveButton = (Button) findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                title = editText.getText().toString();
//                uploadMenu(selectedImage, userID, title, 1);
//
//            }
//        });
    }

    public void getTitleData(String Data){
        Log.d("아아",Data);
    }
    public void getImageData(Uri Data){
        Log.d("아아",Data+"");
    }
    public void getTagData(String Data){
        Log.d("아아",Data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stamp_add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nextButton:

                break;
        }
        return super.onOptionsItemSelected(item);
    } //toolbar

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.room_container, fragment).addToBackStack(null).commit();
    }//fragment 교체 (room_container)


//    public void uploadMenu(Uri fileUri, String userID, String drawerTitle, int share) {
//
//
//        File file = new File(getRealPathFromURI(fileUri)); //절대경로로 바꾸기
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
//        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
//        RequestBody drawerTitleBody = RequestBody.create(MediaType.parse("text/plain"), drawerTitle);
//
//
//        Api Api = APIClient.getClient().create(Api.class);
//        Call<SuccessResponseInfo> call = Api.MenuUpload(requestFile, userIDBody, drawerTitleBody, share);
//        //finally performing the call
//        call.enqueue(new Callback<SuccessResponseInfo>() {
//            @Override
//            public void onResponse(Call<SuccessResponseInfo> call, Response<SuccessResponseInfo> response) {
//                if (response.isSuccessful()) {
//                    setResult(RESULT_OK);
//                    finish();
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//
//
//    private String getRealPathFromURI(Uri contentURI) {
//
//        String result;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = contentURI.getPath();
//
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//
//    } // 절대경로로 변환
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        Bitmap bm;
//        if (requestCode == 0) { //제공된 사진 선택
//            if (intent != null) {
//                resId = intent.getIntExtra("resId", 0);
//                imageView.setImageResource(resId);
//            }
//        } else if (requestCode == 1) { //갤러리 사진 선택
//            if (intent != null) {
//                //              try {
//
//                selectedImage = intent.getData();
//                imageView.setImageURI(selectedImage);
//
//            }
//
//        }

//    }


}
