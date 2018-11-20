package com.org.rxsimple.net.down;

import android.os.Environment;
import android.text.TextUtils;

import com.org.rxsimple.net.CacheInterceptor;
import com.org.rxsimple.net.HttpConfig;
import com.org.rxsimple.net.HttpsHostnameVerifier;
import com.org.rxsimple.net.LoggingInterceptor;
import com.org.rxsimple.net.RetrofitManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by guozhk
 * Date : 2018/11/20
 * Dre :
 **/
public class DownLoadManager {

    private String TAG = DownLoadManager.class.getSimpleName();
    private Retrofit mRetrofit;

    private static class DownLoadManagerHolder {
        private static DownLoadManager INSTANCE = new DownLoadManager();
    }

    public static DownLoadManager getInstanse() {
        return DownLoadManager.DownLoadManagerHolder.INSTANCE;
    }


    private DownLoadManager() {
        //initRetrofit();
    }

    private void initRetrofit(IProcessListener listener) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttp(listener))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkhttp(IProcessListener listener) {

        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(HttpConfig.CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.WRITE_TIME, TimeUnit.SECONDS)
                // .cache(cache)
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new DownloadInterceptor(listener))
                //暂时这样处理
                // .sslSocketFactory(HttpsFactory.getSSLSocketFactory(App.getApp(), HttpConfig.certificates))
                .hostnameVerifier(new HttpsHostnameVerifier(HttpConfig.BASE_URLS))
                .build();
    }

    public <T> T createService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    public <T> T download(Class<T> serviceClass, String url, String fileSavePath, IProcessListener listener) {
        if (TextUtils.isEmpty(url)) {
            if (listener != null) {
                listener.onFail("下载地址为空");
            }
            return null;
        }
        if (TextUtils.isEmpty(fileSavePath)) {
            if (listener != null) {
                listener.onFail("文件保存地址为空");
            }
            return null;
        }
        initRetrofit(listener);

        return createService(serviceClass);

    }


}
