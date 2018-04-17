package com.example.simple;

import android.app.Application;

import com.example.simple.api.RetrofitManager;

/**
 * Created by guozhk on 2018/4/17.
 */

public class RetrofitApp extends Application {

    public static RetrofitApp mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
        RetrofitManager.getInstanse();
    }
}
