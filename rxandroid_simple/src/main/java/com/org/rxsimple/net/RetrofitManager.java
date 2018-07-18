package com.org.rxsimple.net;

import android.os.Environment;

import java.io.File;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guozhk on 2018/4/12.
 */

public class RetrofitManager {
    private String TAG = RetrofitManager.class.getSimpleName();
    private Retrofit mRetrofit;

    private static class RetrofitManagerHolder {
        private static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstanse() {
        return RetrofitManagerHolder.INSTANCE;
    }


    private RetrofitManager() {
        initRetrofit();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private OkHttpClient getOkhttp() {
        File cacheFile = new File(Environment.getExternalStorageDirectory(), "netCache");
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);//google建议放到这里
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(HttpConfig.CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.WRITE_TIME, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .build();
    }


    public <T> T createService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

}
