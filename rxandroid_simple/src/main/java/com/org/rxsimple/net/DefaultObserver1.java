package com.org.rxsimple.net;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * create by guozhk on 2018/7/17
 **/
public class DefaultObserver1<T> implements Observer<BaseEntity<T>> {

    private String TAG = "Result";
    @Override
    public void onSubscribe(Disposable d) {
        Log.e(TAG,"onSubscribe ....");
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        Log.e(TAG,"onNext ...."+tBaseEntity.getContent());
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG,"onError ...."+e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.e(TAG,"onComplete ....");

    }
}
