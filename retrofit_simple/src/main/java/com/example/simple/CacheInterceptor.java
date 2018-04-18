package com.example.simple;

import android.util.Log;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guozhk on 2018/4/17.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtil.isNetworkAvalible(RetrofitApp.mApp)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        Log.e("Interceptor", "Cache net:" + NetUtil.isNetworkAvalible(RetrofitApp.mApp));
        String cacheControl = request.cacheControl().toString();
        Log.e("Interceptor", " Cache  cacheControl:" + cacheControl);
        if (NetUtil.isNetworkAvalible(RetrofitApp.mApp)) {
            // String cacheControl =request.cacheControl().toString();
            int maxAge = 1 * 60; // 有网络时 设置缓存超时时间为60s;
            response.newBuilder()
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24; // 无网络时，设置超时为1天
            response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
//        int maxAge = 1 * 60; // 有网络时 设置缓存超时时间为60s;
//        response.newBuilder()
//                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                .header("Cache-Control", "public, max-age=" + maxAge)
//                .build();

        return response;


//        Request request = chain.request();
//        Response response = chain.proceed(request);
//
//        if (NetUtil.isNetworkAvalible(RetrofitApp.mApp)) {
//            int maxAge = 60 * 60 * 24 * 2;//缓存失效时间，单位为秒
//            return response.newBuilder()
//                    .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .header("Cache-Control", "public ,max-age=" + maxAge)
//                    .build();
//        }
//        return response;
    }
}
