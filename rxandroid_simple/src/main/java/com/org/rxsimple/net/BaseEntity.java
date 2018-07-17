package com.org.rxsimple.net;

import java.io.Serializable;

/**
 * create by guozhk on 2018/7/13
 **/
public class BaseEntity<T> implements Serializable {
    private int status_code;
    private String error_msg;
    private T data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
