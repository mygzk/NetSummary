package com.org.rxsimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.org.rxsimple.apiservice.NetServiceManager;
import com.org.rxsimple.net.NetCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                testLogin();
                break;
            default:
                break;
        }
    }

    private void testLogin() {

        NetServiceManager.getInstance().login("13977777777", "123456abc",
                netCallback);

    }

    NetCallback<LoginEnty> netCallback = new NetCallback<LoginEnty>() {
        @Override
        public void result(LoginEnty o) {
            Log.e("res","==result=====");
        }

        @Override
        public void fail(String msg) {
            Log.e("res","==fail=====msg:"+msg);
        }
    };
}
