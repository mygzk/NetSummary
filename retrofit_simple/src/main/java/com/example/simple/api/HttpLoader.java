package com.example.simple.api;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guozhk on 2018/5/7.
 */

public class HttpLoader {

    private HttpService mHttpService;

    private HttpLoader() {
        mHttpService = RetrofitManager.getInstanse().initService(HttpService.class);
    }

    private static class SingletonHolder {
        private static final HttpLoader INSTANCE = new HttpLoader();
    }

    public static HttpLoader getLoader() {
        return SingletonHolder.INSTANCE;
    }


    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
