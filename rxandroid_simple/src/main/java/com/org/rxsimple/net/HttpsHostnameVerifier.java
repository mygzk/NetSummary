package com.org.rxsimple.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Create by guozhk
 * Date : 2018/11/9
 * Dre :验证主机地址
 **/
public class HttpsHostnameVerifier implements HostnameVerifier {
    String hosts[];

    public HttpsHostnameVerifier(String[] hosts) {
        this.hosts = hosts;
    }

    @Override
    public boolean verify(String hostname, SSLSession session) {
        boolean ret = false;
        for (String host : hosts) {
            if (host.equalsIgnoreCase(hostname)) {
                ret = true;
            } else {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(hostname, session);
            }
        }
        return ret;
    }

}
