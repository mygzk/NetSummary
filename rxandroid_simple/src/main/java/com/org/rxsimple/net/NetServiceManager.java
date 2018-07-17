package com.org.rxsimple.net;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * create by guozhk on 2018/7/17
 **/
public class NetServiceManager {
    private static NetServiceManager netServiceManager;

    public static class NetServiceManagerInstance {
        private static NetServiceManager INSTANCE = new NetServiceManager();
    }

    private NetServiceManager() {
    }

    public static NetServiceManager getInstance() {
        return NetServiceManagerInstance.INSTANCE;
    }

    public void login(String phone, String pwd) {
        Observable observable = RetrofitManager.getInstanse().createService(NetService.class)
                .login(phone, pwd);
        RxUtil.compose(observable).subscribe(new DefaultObserver());


    }

    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return null;
    }


}
