package com.org.rxsimple.apiservice;

import com.org.rxsimple.LoginEnty;
import com.org.rxsimple.net.ApiSubscribe;
import com.org.rxsimple.net.BaseEntity;
import com.org.rxsimple.net.DefaultTransformer;
import com.org.rxsimple.net.NetCallback;
import com.org.rxsimple.net.RetrofitManager;

/**
 * create by guozhk on 2018/7/17
 **/
public class NetServiceManager {

    private NetService mNetService;

    public static class NetServiceManagerInstance {
        private static NetServiceManager INSTANCE = new NetServiceManager();
    }

    private NetServiceManager() {
        mNetService = RetrofitManager.getInstanse().createService(NetService.class);
    }

    public static NetServiceManager getInstance() {
        return NetServiceManagerInstance.INSTANCE;
    }


    public void login(String phone, String pwd, NetCallback callback) {
        mNetService.login(phone, pwd)
                .compose(new DefaultTransformer<BaseEntity<LoginEnty>>())
                .subscribe(new ApiSubscribe<LoginEnty>(callback));
    }


}
