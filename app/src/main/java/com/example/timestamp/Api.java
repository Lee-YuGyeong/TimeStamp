package com.example.timestamp;

import com.example.timestamp.MyResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    //the base URL for our API
    //make sure you are not using localhost
    //find the ip usinc ipconfig command
    String BASE_URL = "http://lyg6452.dothome.co.kr/";

    //this is our multipart request
    //we have two parameters on is name and other one is description
    @Multipart
    @POST("Api.php?apicall=upload")
    Call<MyResponse> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("desc") RequestBody desc);

}