package com.org.rxsimple.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Application interceptors应用程序拦截器
 * 不需要担心比如重定向和重试的中间响应。
 * 总是被调用一次，即使HTTP响应结果是从缓存中获取的。
 * 监控应用程序的原始意图。不关心例如OkHttp注入的头部字段If-None-Match。
 * 允许短路，不调用Chain.proceed（）。
 * 允许重试并多次调用Chain.proceed（）。
 * <p>
 * Network Interceptors网络拦截器
 * 能够对中间的响应进行操作比如重定向和重试。
 * 当发生网络短路时，不调用缓存的响应结果。
 * 监控数据，就像数据再网络上传输一样。
 * 访问承载请求的连接Connection。
 */
public class LoggingInterceptor implements Interceptor {
    private String TAG = LoggingInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain)  {
        Request request = chain.request();
        long t1 = System.nanoTime();
        Log.e(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = null;
        try {
            response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.e(TAG,   "Received response for "+response.request().url()+
                    " in "+ (t2 - t1)+ " "+response.headers()+" connection="+chain.connection());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
