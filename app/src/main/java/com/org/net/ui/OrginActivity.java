package com.org.net.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.org.net.R;
import com.org.net.base.BaseActivity;

public class OrginActivity extends BaseActivity {

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
                break;
            default:
                break;
        }
    }
}
