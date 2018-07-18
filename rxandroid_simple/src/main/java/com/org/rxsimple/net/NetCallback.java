package com.org.rxsimple.net;

/**
 * create by guozhk on 2018/7/18
 **/
public interface NetCallback<T> {

    void result(T t);

    void fail(String msg);
}
