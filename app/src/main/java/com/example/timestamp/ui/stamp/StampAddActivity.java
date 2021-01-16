package com.example.timestamp.ui.stamp;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.R;
import com.example.timestamp.StampAddMenuImageFragment;
import com.example.timestamp.StampAddMenuTagFragment;
import com.example.timestamp.StampAddMenuTitleFragment;
import com.example.timestamp.SuccessResponseInfo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StampAddActivity extends AppCompatActivity {

    String userID;

    Toolbar toolbar;

    String title;
    Uri image;
    String tag;

    int share;

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

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        userID = sharedPreferences.getString("userID", "null");
        share = sharedPreferences.getInt("share", -1);


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }//뒤로가기할때 열려있는 키보드 닫기

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.room_container, fragment).addToBackStack(null).commit();
    }//fragment 교체 (room_container)

    public void uploadMenu_my() {


        File file = new File(getRealPathFromURI(image)); //절대경로로 바꾸기

        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(image)), file);
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), title);

        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.MenuUpload_my(requestFile, userIDBody, titleBody, share);
        //finally performing the call
        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, Response<SuccessResponseInfo> response) {
                if (response.isSuccessful()) {
                    setResult(RESULT_OK);
                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {

            }
        });

    }

    public void uploadMenu_share() {


        File file = new File(getRealPathFromURI(image)); //절대경로로 바꾸기

        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(image)), file);
        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody tagBody = RequestBody.create(MediaType.parse("text/plain"), tag);

        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.MenuUpload_share(requestFile, userIDBody, titleBody, share, tagBody);
        //finally performing the call
        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, Response<SuccessResponseInfo> response) {
                if (response.isSuccessful()) {
                    setResult(RESULT_OK);
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


    public void getTitleData(String data) {
        title = data;
    }

    public void getImageData(Uri data) {
        image = data;
    }

    public void getTagData(String data) {
        tag = data;
    }

}
