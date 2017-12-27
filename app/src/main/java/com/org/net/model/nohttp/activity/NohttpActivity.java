package com.org.net.model.nohttp.activity;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.org.net.R;
import com.org.net.base.BaseActivity;
import com.org.net.base.inteface.IBaseView;
import com.org.net.constant.Constants;
import com.org.net.model.nohttp.presenter.NohttpPresenter;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

public class NohttpActivity extends BaseActivity implements IBaseView {

    private TextView tvResult;

    private NohttpPresenter mNohttpPresenter;

    @Override
    protected int getLayoutId() {
        mNohttpPresenter = new NohttpPresenter(this);
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
                mNohttpPresenter.requestOrigin();
                break;
            default:
                break;
        }
    }

    private void requestOrigin() {

    }


    @Override
    public void start() {

    }

    @Override
    public void fail() {

    }

    @Override
    public void result(Object object) {

    }
}
