package com.org.rxsimple.net;

import java.io.Serializable;

/**
 * create by guozhk on 2018/7/13
 **/
public class BaseEntity<T> implements Serializable {
    private int code;
    private String errorMsg;
    private T content;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    /**
     * 根据业务具体判断
     *
     * @return boolean
     */
    public boolean isSuccess() {
        if (code != 0) {
            success = false;
        } else {
            success = true;
        }
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}


