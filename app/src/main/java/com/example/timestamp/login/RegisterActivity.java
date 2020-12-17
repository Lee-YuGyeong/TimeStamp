package com.example.timestamp.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.timestamp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name;
    private Button btn_register, btn_val;
    String userID, userPassword, userName;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);


        btn_val = findViewById(R.id.btn_val);
        btn_val.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDValidateCheck();
            }
        });


        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteRegister();
            }
        });
    }

    public void IDValidateCheck() {

        userID = et_id.getText().toString();
        userPassword = et_pass.getText().toString();
        userName = et_name.getText().toString();

        if (validate) {
            return;
        }
        if (userID.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
            return;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                                .setPositiveButton("확인", null)
                                .create();
                        dialog.show();
                        et_id.setEnabled(false);
                        validate = true;
                        btn_val.setText("확인");
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("이미 존재하는 아이디입니다.")
                                .setNegativeButton("확인", null)
                                .create();
                        dialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        IDValidateRequest validateRequest = new IDValidateRequest(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(validateRequest);

    }

    public void CompleteRegister() {
        userID = et_id.getText().toString();
        userPassword = et_pass.getText().toString();
        userName = et_name.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    //회원가입 성공시
                    if (success) {
                        Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                        finish();
                        //회원가입 실패시
                    } else {
                        Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        // 서버로 Volley를 이용해서 요청을 함
        RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userName, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(registerRequest);

    }
}

