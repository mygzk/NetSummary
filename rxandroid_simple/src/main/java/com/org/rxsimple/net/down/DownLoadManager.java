package com.org.rxsimple.net.down;

import android.text.TextUtils;

import com.org.rxsimple.net.CacheInterceptor;
import com.org.rxsimple.net.DefaultTransformer;
import com.org.rxsimple.net.HttpConfig;
import com.org.rxsimple.net.HttpLogger;
import com.org.rxsimple.net.HttpsHostnameVerifier;
import com.org.rxsimple.net.LoggingInterceptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
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
                .baseUrl(HttpConfig.BASE_URL1)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttp(listener))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkhttp(IProcessListener listener) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());//创建拦截对象
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这一句一定要记得写，否则没有数据输出
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(HttpConfig.CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.WRITE_TIME, TimeUnit.SECONDS)
                .addNetworkInterceptor(logInterceptor)
                // .cache(cache)
              //  .addInterceptor(new LoggingInterceptor())
              //  .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new DownloadInterceptor(listener))
                //暂时这样处理
                // .sslSocketFactory(HttpsFactory.getSSLSocketFactory(App.getApp(), HttpConfig.certificates))
              //  .hostnameVerifier(new HttpsHostnameVerifier(HttpConfig.BASE_URLS))
                .build();
    }

    private <T> T createService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    /**
     * 下载
     *
     * @param url          url
     * @param fileSavePath fileSavePath
     * @param listener     listener
     */
    public void download(String url, final String fileSavePath, final IProcessListener listener) {
        if (TextUtils.isEmpty(url)) {
            if (listener != null) {
                listener.onFail("下载地址为空");
            }
            return;
        }
        if (TextUtils.isEmpty(fileSavePath)) {
            if (listener != null) {
                listener.onFail("文件保存地址为空");
            }
            return;
        }
        initRetrofit(listener);

      //  DownloadService downloadService = createService(DownloadService.class);
        createService(DownloadService.class).download(url)
                .compose(new DefaultTransformer<ResponseBody>())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (listener != null) {
                            listener.onStart();
                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (responseBody != null) {
                            writeFile(responseBody.byteStream(), fileSavePath, listener);
                        } else {
                            if (listener != null) {
                                listener.onFail("下载失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (listener != null) {
                            listener.onFinishDownload();
                        }
                    }
                });

    }


    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    private void writeFile(InputStream inputString, String filePath, IProcessListener listener) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();

        } catch (FileNotFoundException e) {
            listener.onFail("FileNotFoundException");
        } catch (IOException e) {
            listener.onFail("IOException");
        }
    }

}
