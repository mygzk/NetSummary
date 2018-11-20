package com.org.rxsimple.net.down;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Create by guozhk
 * Date : 2018/11/20
 * Dre :带进度 下载  拦截器
 **/
public class DownloadInterceptor implements Interceptor {


    private IProcessListener downloadListener;

    public DownloadInterceptor(IProcessListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new DownResponseBody(response.body(), downloadListener)).build();
    }
}
