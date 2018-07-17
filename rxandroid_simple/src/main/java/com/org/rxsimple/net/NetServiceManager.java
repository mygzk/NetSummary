package com.org.rxsimple.net;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.*;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * create by guozhk on 2018/7/17
 **/
public class NetServiceManager {

    private NetService mNetService;

    public static class NetServiceManagerInstance {
        private static NetServiceManager INSTANCE = new NetServiceManager();
    }

    private NetServiceManager() {
        mNetService =  RetrofitManager.getInstanse().createService(NetService.class);
    }

    public static NetServiceManager getInstance() {
        return NetServiceManagerInstance.INSTANCE;
    }

    public void  login(String phone, String pwd) {

        mNetService.login(phone, pwd)
                .compose(new DefaultTransformer<BaseEntity<String>>())
//                .map(new Function<BaseEntity<String>, String>() {
//                    @Override
//                    public String apply(BaseEntity<String> stringBaseEntity) throws Exception {
//                        return stringBaseEntity;
//                    }
//                })
                .subscribe(new DefaultObserver1<String>());



    }

//    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//        return null;
//    }


}
