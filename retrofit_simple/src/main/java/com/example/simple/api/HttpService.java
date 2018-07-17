package com.example.simple.api;


import android.database.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;

import retrofit2.http.POST;

/**
 * Created by guozhk on 2018/4/12.
 */

public interface HttpService {
    @HTTP(method = "GET", path = "news", hasBody = false)
    Call<ResponseBody> getAllBlog();


    @FormUrlEncoded
    @POST("member/login/login")
    Call<ResponseBody> login( @Field("phone") String phone, @Field("secretPasswd") String pwd);

    @GET("info")
    Call<ResponseBody> getInfo();
    @GET("news")
    Call<ResponseBody> gettest();

    @GET("getPapers")
    Call<ResponseBody> getPapers(@Header("token") String token);



    <T> Observable<T> getTop250();





}




