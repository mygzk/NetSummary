package com.org.rxsimple.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * create by guozhk on 2018/7/17
 **/
public class RxUtil {
    public static <T> Observable compose(Observable<T> observable) {
        return observable.compose(new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(new DefaultTransformer<BaseEntity<String>>());
            }
        });
    }






}
