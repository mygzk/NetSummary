package com.org.net.ui;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.org.net.R;
import com.org.net.base.BaseActivity;
import com.org.net.constant.Constants;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

public class OrginActivity extends BaseActivity {
    /**
     * 用来标志请求的what, 类似handler的what一样，这里用来区分请求。
     */
    private static final int NOHTTP_WHAT_TEST = 0x001;
    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    private TextView tvResult;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orgin;
    }

    @Override
    protected void initView() {
        tvResult = queryViewById(R.id.nohttp_request_result);
        queryViewById(R.id.nohttp_request_origin, true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nohttp_request_origin:
                requestOrigin();
                break;
            default:
                break;
        }
    }

    private void requestOrigin() {
        mQueue = NoHttp.newRequestQueue();
        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest(Constants.URL_NOHTTP_JSONOBJECT, RequestMethod.GET);
        request.add("name", "yanzhenjie") // String型。
                .add("pwd", 123) // int型。
                .add("userAge", 1.25) // double型。
                .add("nooxxx", 1.2F) // flocat型。

                // 单个请求的超时时间，不指定就会使用全局配置。
                .setConnectTimeout(10 * 1000) // 设置连接超时。
                .setReadTimeout(20 * 1000) // 设置读取超时时间，也就是服务器的响应超时。

                // 请求头，是否要添加头，添加什么头，要看开发者服务器端的要求。
                .addHeader("Author", "sample")
                .setHeader("User", "Jason")

                // 设置一个tag, 在请求完(失败/成功)时原封不动返回; 多数情况下不需要。
                .setTag(new Object())
                // 设置取消标志。
                .setCancelSign(this);
        mQueue.add(100, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                Log.e(TAG, "onStart what:" + what);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e(TAG, "onSucceed what:" + what + " response: " + response.get());
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG, "onFailed what:" + what + " response: " + response.toString());
            }

            @Override
            public void onFinish(int what) {
                Log.e(TAG, "onFinish what:" + what);
            }
        });

    }
}
