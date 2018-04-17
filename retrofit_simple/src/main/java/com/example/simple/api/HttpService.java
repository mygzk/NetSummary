package com.example.simple.api;


import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by guozhk on 2018/4/12.
 */

public interface HttpService {
    @HTTP(method = "GET", path = "news", hasBody = false)
    Call<ResponseBody> getAllBlog();

    /**
     * method 表示请求的方法，区分大小写，retrofit 不会做处理
     * path表示路径
     * hasBody表示是否有请求体
     */
    @HTTP(method = "GET", path = "news/{id}")
    Call<ResponseBody> getBlog(@Path("id") int id);


    //@Headers({"Content-Type: application/json","Accept: application/json"})
    //@POST("member/login/login")
    //Call<ResponseBody> login(@HeaderMap Map<String, String> headers, @Body RequestBody body);
    @FormUrlEncoded
    @POST("member/login/login")
    Call<ResponseBody> login( @Field("phone") String phone, @Field("secretPasswd") String pwd);
    //Call<ResponseBody> login(@HeaderMap Map<String, String> headers, @Field("phone") String phone, @Field("secretPasswd") String pwd);

    @GET("info")
    Call<ResponseBody> getInfo();
    @GET("news")
    Call<ResponseBody> gettest();
}




