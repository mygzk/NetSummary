package com.example.simple.api;

import android.os.Environment;
import android.util.Log;

import com.example.simple.CacheInterceptor;
import com.example.simple.RetrofitApp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guozhk on 2018/4/12.
 */

public class RetrofitManager {
    private String TAG = RetrofitManager.class.getSimpleName();
    private static RetrofitManager manager;
    private Retrofit mRetrofit;
    private HttpService mHttpService;


    public static RetrofitManager getInstanse() {
        if (manager == null) {
            synchronized (RetrofitManager.class) {
                if (manager == null) {
                    manager = new RetrofitManager();
                }
            }
        }
        return manager;
    }


    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .client(getOkhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mHttpService = mRetrofit.create(HttpService.class);
    }


    private OkHttpClient getOkhttp() {

        // File cacheFile = new File(RetrofitApp.mApp.getCacheDir(), "net_cache_Data");
        Log.e("getOkhttp", "path:" + Environment.getExternalStorageDirectory());
        //Log.e("getOkhttp","cacheFile path:"+cacheFile.getAbsolutePath());
        File cacheFile = new File(Environment.getExternalStorageDirectory(), "net_cache_Data");
        Log.e("getOkhttp", " cacheFile  path:" +cacheFile.getAbsolutePath());
        //File cacheFile = new File(Environment.getExternalStorageDirectory(), "sdcard/net_cache_Data");
        //设置缓存大小
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);//google建议放到这里

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(15, TimeUnit.SECONDS)//超时时间15S
                .readTimeout(15, TimeUnit.SECONDS)
                // .writeTimeout(15, TimeUnit.SECONDS)
                // .addInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .cache(cache)
                .build();

        return client;
    }


    public HttpService getHttpService() {
        return mHttpService;
    }

}
