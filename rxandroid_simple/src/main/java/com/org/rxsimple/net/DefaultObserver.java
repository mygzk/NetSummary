package com.org.rxsimple.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * create by guozhk on 2018/7/17
 **/
public class DefaultObserver<T> implements Observer<BaseEntity<T>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    /*  Callback callback;

    public DefaultObserver(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }*/
}
