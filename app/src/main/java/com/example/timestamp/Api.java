package com.example.timestamp;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    String BASE_URL = "http://lyg6452.dothome.co.kr/";

    @Multipart
    @POST("Api.php?apicall=upload")
    Call<List<MyResponse>> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("userID") RequestBody userID, @Part("drawerName") RequestBody drawerName);

    @Multipart
    @POST("Api.php?apicall=getallimages")
    Call<ResponseInfo> getimages(@Part("userID") RequestBody userID);
}