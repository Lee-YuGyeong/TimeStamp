package com.example.timestamp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.timestamp.ui.home.HomeFragment;
import com.example.timestamp.ui.stamp.StampFragment;
import com.example.timestamp.ui.ourStamp.OurStampFragment;
import com.example.timestamp.ui.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static Context mContext;

     private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment homeFragment = new HomeFragment();
    private StampFragment stampFragment = new StampFragment();
    private OurStampFragment ourStampFragment = new OurStampFragment();
    private SettingFragment settingFragment = new SettingFragment();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, homeFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        transaction.replace(R.id.nav_host_fragment, homeFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_myStamp:
                        transaction.replace(R.id.nav_host_fragment, stampFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_ourStamp:
                        transaction.replace(R.id.nav_host_fragment, ourStampFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_setting:
                        transaction.replace(R.id.nav_host_fragment, settingFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });

        checkSelfPermission();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //권한을 허용 했을 경우
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity", "권한 허용 : " + permissions[i]);
                }
            }
        }


    }

    public void checkSelfPermission() {
        String temp = "";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.CAMERA + " ";
        }//파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        } //파일 쓰기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }
        if (TextUtils.isEmpty(temp) == false) { // 권한 요청

            ActivityCompat.requestPermissions(this, temp.trim().split(" "), 1);
        } else { // 모두 허용 상태
        }
    }

    public void networkStatus() {
        int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
        if (status == NetworkStatus.TYPE_MOBILE) {
            Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있습니다.", Toast.LENGTH_LONG).show();
        } else if (status == NetworkStatus.TYPE_WIFI) {
            Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있습니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있지 않습니다. 인터넷을 연결해주세요.", Toast.LENGTH_LONG).show();
        }
    }//네트워크 연결 여부 //사용방법 ((MainActivity)MainActivity.mContext).networkStatus();

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.main_toolbar_menu, menu);
//        return true;
//
//    }
//
//
//    // 툴바에 삽입된 메뉴에 대해서 이벤트 처리
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.search_icon:
//                Toast.makeText(getApplicationContext(), "검색 버튼이 클릭됨", Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.write_icon:
//                Toast.makeText(getApplicationContext(), "글쓰기 버튼이 클릭됨", Toast.LENGTH_LONG).show();
//                return true;
//            default:
//                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
//                return super.onOptionsItemSelected(item);
//        }
//    }

}

