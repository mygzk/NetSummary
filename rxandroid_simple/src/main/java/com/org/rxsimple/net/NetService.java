package com.org.rxsimple.net;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetService {
    @FormUrlEncoded
    @POST("member/login/login")
    Observable<BaseEntity<String> > login(@Field("phone") String phone, @Field("secretPasswd") String pwd);


}
