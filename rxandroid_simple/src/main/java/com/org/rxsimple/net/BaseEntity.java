package com.org.rxsimple.net;

import java.io.Serializable;

/**
 * create by guozhk on 2018/7/13
 **/
public class BaseEntity<T> implements Serializable {
    private int code;
    private String msg;
    private T content;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}


