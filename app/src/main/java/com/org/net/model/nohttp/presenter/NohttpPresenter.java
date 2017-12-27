package com.org.net.model.nohttp.presenter;

import android.util.Log;

import com.org.net.base.inteface.IBaseView;
import com.org.net.constant.Constants;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by guozhk
 * create time on 2017/12/27.
 */

public class NohttpPresenter {
    private String TAG = NohttpPresenter.class.getSimpleName();
    IBaseView mView;

    public NohttpPresenter(IBaseView mView) {
        this.mView = mView;
    }

    /**
     * NoHttp 原生用法
     */
    public void requestOrigin() {
        RequestQueue mQueue = NoHttp.newRequestQueue();
        // 创建请求对象。
        final Request<String> request =
                NoHttp.createStringRequest(Constants.URL_NOHTTP_JSONOBJECT, RequestMethod.GET);
        request.add("name", "yanzhenjie") // String型。
                .add("pwd", 123) // int型。
                .add("userAge", 1.25) // double型。
                .add("nooxxx", 1.2F) // flocat型。

                // 单个请求的超时时间，不指定就会使用全局配置。
                .setConnectTimeout(10 * 1000) // 设置连接超时。
                .setReadTimeout(20 * 1000) // 设置读取超时时间，也就是服务器的响应超时。

                // 请求头，是否要添加头，添加什么头，要看开发者服务器端的要求。
                .addHeader("Author", "sample")
                .setHeader("User", "Jason")

                // 设置一个tag, 在请求完(失败/成功)时原封不动返回; 多数情况下不需要。
                .setTag(new Object())
                // 设置取消标志。
                .setCancelSign(this);
        mQueue.add(100, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                Log.e(TAG, "onStart what:" + what);
                mView.start();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e(TAG, "onSucceed what:" + what + " response: " + response.get());
                mView.result(response);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG, "onFailed what:" + what + " response: " + response.toString());
                mView.fail();
            }

            @Override
            public void onFinish(int what) {
                Log.e(TAG, "onFinish what:" + what);
            }
        });
    }






}
