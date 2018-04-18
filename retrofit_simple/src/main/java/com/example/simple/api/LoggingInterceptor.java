package com.example.simple.api;

import android.util.Log;

import com.example.simple.NetUtil;
import com.example.simple.RetrofitApp;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guozhk on 2018/4/17.
 * 日志拦截器
 */

public class LoggingInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!NetUtil.isNetworkAvalible(RetrofitApp.mApp)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        long t1 = System.nanoTime();
        Log.e("Interceptor","Logging "+String.format("Sending request %s on %s%n%s", request.url(),
                chain.connection(), request.headers()));


        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Log.e("Interceptor",String.format("Logging Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
