package com.example.timestamp.API;
import com.example.timestamp.ErrorResponseInfo;
import com.example.timestamp.ImageDetailResponseInfo;
import com.example.timestamp.MenuResponseInfo;
import com.example.timestamp.ShareRecyclerViewResponseInfo;
import com.example.timestamp.SuccessResponseInfo;
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
    @POST("MenuUpload.php")
    Call<SuccessResponseInfo> MenuUpload(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("userID") RequestBody userID, @Part("title") RequestBody title, @Part("share") int share); //Menu 데이터 업로드

    @Multipart
    @POST("MenuApi.php?apicall=getinfo")
    Call<MenuResponseInfo> MenuGet(@Part("userID") RequestBody userID); //Menu 데이터 받기

    @Multipart
    @POST("ImageApi.php?apicall=upload")
    Call<ErrorResponseInfo> ImageUpload(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("num") int num, @Part("userID") RequestBody userID); //myImage 데이터 업로드

    @Multipart
    @POST("ImageApi.php?apicall=get")
    Call<ImageDetailResponseInfo> ImageGet(@Part("num") int num); //myImage 데이터 받기

    @Multipart
    @POST("ShareRecyclerViewApi.php?apicall=get")
    Call<ShareRecyclerViewResponseInfo> ShareGet(@Part("num") int num); //ShareRecyclerView 데이터 받기

}