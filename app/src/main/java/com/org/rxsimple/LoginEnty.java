package com.org.rxsimple;

/**
 * Created by guozhk on 2018/7/17.
 */

public class LoginEnty {


    /**
     * code : -1
     * content : null
     * msg : 用户名或密码错误多次将被锁定!
     * success : false
     */

    private int code;
    private String content;
    private String msg;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
