package com.example.timestamp.API;
import com.example.timestamp.MenuDetailResponseInfo;
import com.example.timestamp.MyResponse;
import com.example.timestamp.ResponseInfo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {


    @Multipart
    @POST("MyMenuApi.php?apicall=upload")
    Call<MyResponse> MyMenuUpload(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("userID") RequestBody userID, @Part("drawerName") RequestBody drawerName); //myMenu 데이터 업로드

    @Multipart
    @POST("MyMenuApi.php?apicall=getinfo")
    Call<ResponseInfo> MyMenuGet(@Part("userID") RequestBody userID); //myMenu 데이터 받기

    @Multipart
    @POST("MyImageApi.php?apicall=upload")
    Call<MyResponse> MyImageUpload(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("myNum") int myNum); //myMenu 데이터 업로드

    @Multipart
    @POST("MyImageApi.php?apicall=getinfo")
    Call<MenuDetailResponseInfo> MyImageGet(@Part("myNum") int myNum); //myMenu 데이터 받기
}