package com.example.simple.api;

import android.util.Log;

import java.io.IOException;

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


        long t1 = System.nanoTime();
        System.out.println(String.format("Sending request %s on %s%n%s", request.url(),
                chain.connection(), request.headers()));


        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        System.out.println(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        return response;
    }
}
