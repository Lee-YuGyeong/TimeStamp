package com.example.timestamp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.timestamp.MainActivity;
import com.example.timestamp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_pass;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String login = sharedPreferences.getString("userID", "");

        if(login != "") {
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

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {//로그인 성공시

                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
//                                String userName = jsonObject.getString("userName");
//                                String userAge = jsonObject.getString("userAge");

                                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                                InLogDate(userID, userPass);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
//                                intent.putExtra("userName", userName);
//                                intent.putExtra("userAge", userAge);

                                startActivity(intent);

                            } else {//로그인 실패시
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });
    }

    public void InLogDate(String id, String password) { // SharedPreferences에 값 저장.

        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", id);
        editor.putString("userPassword", password);
        editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

    }
//
//    public void OutLogData(String id, String password) {     // SharedPreferences에 값 불러오기.
//        SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
//        String inputText = sharedPreferences.getString("inputText", "");
//        textView.setText(inputText);    // TextView에 SharedPreferences에 저장되어있던 값 찍기.
//        Toast.makeText(this, "불러오기 하였습니다..", Toast.LENGTH_SHORT).show();
//    }// clickGetBt()..


}
