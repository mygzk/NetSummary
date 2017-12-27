package com.org.net.base;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by guozhk
 * create time on 2017/12/27.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
