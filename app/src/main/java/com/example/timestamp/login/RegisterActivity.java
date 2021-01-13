package com.example.timestamp.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timestamp.API.APIClient;
import com.example.timestamp.API.Api;
import com.example.timestamp.R;
import com.example.timestamp.SuccessResponseInfo;

import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name;
    private Button btn_register, btn_id, btn_name;
    String userID, userPassword, userName;
    private AlertDialog dialog;
    private boolean validate_id = false;
    private boolean validate_name = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);

        // 한글 만 입력 되도록

// 아래와 같이 EditText에 적용 한다.
        et_id.setFilters(new InputFilter[]{filterAlphaNum});

//아래 소스에서 정규식만 바꿔주면 된다.


        btn_id = findViewById(R.id.btn_id);
        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDValidateCheck();
            }
        });

        btn_name = findViewById(R.id.btn_name);
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameValidateCheck();
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

    public InputFilter filterAlphaNum = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };


    public void IDValidateCheck() {

        userID = et_id.getText().toString();

        if (validate_id) {
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

        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);

        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.IDValidate(userIDBody);

        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, retrofit2.Response<SuccessResponseInfo> response) {

                if (response.isSuccessful()) {

                    SuccessResponseInfo successResponseInfo = response.body();

                    if (successResponseInfo.getSuccess()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("사용할 수 있는 아이디입니다. 사용하시겠습니까?");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        et_id.setEnabled(false);
                                        validate_id = true;
                                        btn_id.setText("확인");
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        builder.show();


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("이미 존재하는 아이디입니다.")
                                .setNegativeButton("확인", null)
                                .create();
                        dialog.show();
                    }


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void nameValidateCheck() {

        userName = et_name.getText().toString();

        if (validate_name) {
            return;
        }
        if (userName.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("닉네임은 빈 칸일 수 없습니다")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
            return;
        }
        RequestBody userNameBody = RequestBody.create(MediaType.parse("text/plain"), userName);

        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.NameValidate(userNameBody);

        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, retrofit2.Response<SuccessResponseInfo> response) {

                if (response.isSuccessful()) {

                    SuccessResponseInfo successResponseInfo = response.body();

                    if (successResponseInfo.getSuccess()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("사용할 수 있는 닉네임입니다. 사용하시겠습니까?");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        et_name.setEnabled(false);
                                        validate_name = true;
                                        btn_name.setText("확인");
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        builder.show();


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        dialog = builder.setMessage("이미 존재하는 닉네임입니다.")
                                .setNegativeButton("확인", null)
                                .create();
                        dialog.show();
                    }


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void CompleteRegister() {

        userID = et_id.getText().toString();
        userPassword = et_pass.getText().toString();
        userName = et_name.getText().toString();

        userPassword = userPassword.replaceAll(" ", "");


        if (validate_id == false) {
            Toast.makeText(getApplicationContext(), "아이디 중복검사를 완료해주세요.", Toast.LENGTH_LONG).show();
            return;
        } else if (userPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
            return;
        } else if (validate_name == false) {
            Toast.makeText(getApplicationContext(), "닉네임 중복검사를 완료해주세요.", Toast.LENGTH_LONG).show();
            return;
        }

        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), userID);
        RequestBody userPasswordBody = RequestBody.create(MediaType.parse("text/plain"), userPassword);
        RequestBody userNameBody = RequestBody.create(MediaType.parse("text/plain"), userName);

        Api Api = APIClient.getClient().create(Api.class);
        Call<SuccessResponseInfo> call = Api.Register(userIDBody, userPasswordBody, userNameBody);

        call.enqueue(new Callback<SuccessResponseInfo>() {
            @Override
            public void onResponse(Call<SuccessResponseInfo> call, retrofit2.Response<SuccessResponseInfo> response) {

                if (response.isSuccessful()) {

                    SuccessResponseInfo successResponseInfo = response.body();

                    if (successResponseInfo.getSuccess()) {
                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입이 실패되었습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<SuccessResponseInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}

