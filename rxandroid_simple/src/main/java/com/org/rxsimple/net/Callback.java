package com.org.rxsimple.net;

/**
 * create by guozhk on 2018/7/17
 **/
public interface Callback<T> {
    void result(T t);

    void fail(String msg);
}
