package com.org.net.bean;

/**
 * Created by guozhk
 * create time on 2017/12/27.
 */

public class ClassBean {
    private Class aClass;
    private String des;

    public ClassBean(Class aClass, String des) {
        this.aClass = aClass;
        this.des = des;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
