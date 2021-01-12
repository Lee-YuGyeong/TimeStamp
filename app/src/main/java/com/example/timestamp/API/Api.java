package com.example.timestamp.API;
import com.example.timestamp.MenuDetailResponseInfo;
import com.example.timestamp.MyResponse;
import com.example.timestamp.ResponseInfo;
import com.example.timestamp.login.SuccessResponseInfo;
import com.example.timestamp.login.LoginResponseInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @Multipart
    @POST("LoginApi.php?apicall=get")
    Call<LoginResponseInfo> Login(@Part("userID") RequestBody userID, @Part("userPassword") RequestBody userPassword); //로그인 데이터 검색

    @Multipart
    @POST("IDValidate.php")
    Call<SuccessResponseInfo> IDValidate(@Part("userID") RequestBody userID); //아이디 중복검사

    @Multipart
    @POST("NameValidate.php")
    Call<SuccessResponseInfo> NameValidate(@Part("userName") RequestBody userName); //닉네임 중복검사

    @Multipart
    @POST("NameValidate.php")
    Call<SuccessResponseInfo> Register(@Part("userID") RequestBody userID,@Part("userPassword") RequestBody userPassword,@Part("userName") RequestBody userName); //회원가입 데이터 삽입

    @Multipart
    @POST("MyMenuApi.php?apicall=upload")
    Call<MyResponse> MyMenuUpload(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("userID") RequestBody userID, @Part("drawerName") RequestBody drawerName); //myMenu 데이터 업로드

    @Multipart
    @POST("MyMenuApi.php?apicall=getinfo")
    Call<ResponseInfo> MyMenuGet(@Part("userID") RequestBody userID); //myMenu 데이터 받기

    @Multipart
    @POST("MyImageApi.php?apicall=upload")
    Call<MyResponse> MyImageUpload(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("myNum") int myNum); //myImage 데이터 업로드

    @Multipart
    @POST("MyImageApi.php?apicall=getinfo")
    Call<MenuDetailResponseInfo> MyImageGet(@Part("myNum") int myNum); //myImage 데이터 받기



}