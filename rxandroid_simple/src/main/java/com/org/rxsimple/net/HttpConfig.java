package com.org.rxsimple.net;

import com.org.rxsimple.R;

/**
 * Created by guozhk on 2018/4/17.
 */

public class HttpConfig {

    public static final long CONNECT_TIME = 10L;
    public static final long READ_TIME = 10L;
    public static final long WRITE_TIME = 10L;
    public static final String BASE_URL = "http://dat.dahuobaoxian.com";

    public static final String[] BASE_URLS = new String[]{BASE_URL};


    public static final int[] certificates = {R.raw.gangpiaowang, R.raw.gangpiaowang_old,
            R.raw.gangpiaowang_new, R.raw.gangpiaowang_test_new, R.raw.gpw_test};
}
