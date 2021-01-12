package com.example.timestamp.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.MainActivity;
import com.example.timestamp.R;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("auto", false) == true) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                login(userID, userPass);
            }
        });
    }

    private void login(final String userID, final String userPass) {

        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody userPassBody = RequestBody.create(MediaType.parse("text/plain"), userPass);

        Api Api = APIClient.getClient().create(Api.class);
        Call<LoginResponseInfo> call = Api.LoginPut(userIDBody, userPassBody);

        call.enqueue(new Callback<LoginResponseInfo>() {
            @Override
            public void onResponse(Call<LoginResponseInfo> call, Response<LoginResponseInfo> response) {

                if (response.isSuccessful()) {

                    LoginResponseInfo loginResponseInfo = response.body();
                    Log.d("아아", loginResponseInfo.getSuccess() + "");

                    if (loginResponseInfo.getSuccess()) {
                        Log.d("아아", loginResponseInfo.getSuccess() + "2");

                        InputNameData(loginResponseInfo.getUserName());

                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("자동로그인에 등록하시겠습니까?");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        InputLogData(userID, userPass, true);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        InputLogData(userID, userPass, false);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                        builder.show();

                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        Log.d("아아", loginResponseInfo.getSuccess() + "3");
                    }


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<LoginResponseInfo> call, Throwable t) {
                Log.d("아아", t.getMessage());
            }
        });
    } // retrofit 데이터 받아오기


    public void InputLogData(String id, String password, boolean auto) { // SharedPreferences에 값 저장.

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", id);
        editor.putString("userPassword", password);
        editor.putBoolean("auto", auto);
        editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

    }

    public void InputNameData(String name) {

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", name);
        editor.commit();

    }

}
