package com.org.rxsimple.net;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Create by guozhk
 * Date : 2018/11/22
 * Dre :
 **/
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.e("HttpLogInfo", message);//okHttp的详细日志会打印出来
    }
}
