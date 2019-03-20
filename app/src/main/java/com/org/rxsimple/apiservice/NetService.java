package com.org.rxsimple.apiservice;

import com.org.rxsimple.LoginEnty;
import com.org.rxsimple.net.BaseEntity;
import com.org.rxsimple.net.RequestJsonBody;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NetService {
    @FormUrlEncoded
    @POST("member/login/login")
    Observable<BaseEntity<LoginEnty>> login(@Field("phone") String phone, @Field("secretPasswd") String pwd);

    @FormUrlEncoded
    @POST("member/login/login")
    Observable<ResponseBody> login1(@Field("phone") String phone, @Field("secretPasswd") String pwd);


    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("dangdang/login")
    Observable<BaseEntity<LoginEnty>> login1(@Body RequestJsonBody info);

}
