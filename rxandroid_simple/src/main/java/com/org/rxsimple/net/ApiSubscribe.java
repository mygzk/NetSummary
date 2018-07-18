package com.org.rxsimple.net;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 对结果处理 进行回调
 * create by guozhk on 2018/7/18
 **/
public class ApiSubscribe<T> implements Observer<BaseEntity<T>> {
    private String TAG = "ApiSubscribe";

    private NetCallback callback;

    public ApiSubscribe(NetCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        if (tBaseEntity == null) {
            callback.fail("data is null...");
            return;
        }

        if (!tBaseEntity.isSuccess()) {
            callback.fail("error msg :" + tBaseEntity.getErrorMsg());
        }

        callback.result(tBaseEntity.getContent());

    }

    /**
     * 发生异常 具体可自己操作
     *
     * @param e e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException || e instanceof UnknownHostException) {
            callback.fail("网络异常，请检查网络");
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            callback.fail("网络不畅，请稍后再试！");
        } else if (e instanceof JsonSyntaxException) {
            callback.fail("数据解析异常");
        } else {
            callback.fail("error:" + e.getMessage());
        }


        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete");
    }


}
