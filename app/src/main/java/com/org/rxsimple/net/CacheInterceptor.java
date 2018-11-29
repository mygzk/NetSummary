package com.org.rxsimple.net;

import android.util.Log;

import com.org.rxsimple.App;
import com.org.rxsimple.NetUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {


    private String TAG = CacheInterceptor.class.getSimpleName();

    //设置缓存时间60s
    private int MAXAGE_NET = 1 * 60;
    //设置缓存时间1天
    private int MAXAGE_NO_NET = 24 * 60 * 60;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e(TAG, "=====CacheInter+ceptor====");
        Request request = chain.request();
        if (!NetUtil.isNetworkAvalible(App.getApp().getApplicationContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        } else {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(MAXAGE_NET, TimeUnit.SECONDS)
                    .build();

            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }

        Response response = chain.proceed(request);

        if (NetUtil.isNetworkAvalible(App.getApp().getApplicationContext())) {
            response.newBuilder()
                    .header("Cache-Control", "public,max-age=" + MAXAGE_NET)
                    .removeHeader("Pragma")
                    .build();
        } else {
            response.newBuilder()
                    .header("Cache-Control", "public,only-if-cached,max-age=" + MAXAGE_NO_NET)
                    .removeHeader("Pragma")
                    .build();
        }


        return response;
    }
}
