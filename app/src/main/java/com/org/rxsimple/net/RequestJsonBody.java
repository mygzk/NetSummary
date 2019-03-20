package com.org.rxsimple.net;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;

/**
 * create by guozhk on 2019/3/19 15:59
 **/
public class RequestJsonBody extends RequestBody {

    private String mContent;

    public RequestJsonBody(@Nullable String content) {
        this.mContent = content;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse("application/json; charset=utf-8");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        sink.write(mContent.getBytes(Util.UTF_8));
    }
}
