package com.org.rxsimple;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.org.rxsimple.apiservice.NetServiceManager;
import com.org.rxsimple.net.NetCallback;
import com.org.rxsimple.net.down.DownLoadManager;
import com.org.rxsimple.net.down.IProcessListener;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.tv_down).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                testLogin();
                break;
            case R.id.tv_down:
                testDown();
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
            Log.e("res", "==result=====");
        }

        @Override
        public void fail(String msg) {
            Log.e("res", "==fail=====msg:" + msg);
        }
    };


    private void testDown() {

        String filePath = Environment.getExternalStorageDirectory() + File.separator + "test.apk";
     //   String url = "http://gdownyf.baijincdn.cn/data/wisegame/3ae2751f58133c7b/shoujitaobao_218.apk";
        String url = "http://mobile.fuqim.com/c/release/app-release_c_17_1.0.16_170_sandbox_new2.apk";

        DownLoadManager.getInstanse().download(url, filePath, new IProcessListener() {
            @Override
            public void onStart() {
                Log.e(TAG, "onStart");
            }

            @Override
            public void onProgress(int progress) {
                Log.e(TAG, "onProgress:" + progress);
            }

            @Override
            public void onFinishDownload() {
                Log.e(TAG, "onFinishDownload");
            }

            @Override
            public void onFail(String errorInfo) {
                Log.e(TAG, "onFail:" + errorInfo);
            }
        });


    }
}
