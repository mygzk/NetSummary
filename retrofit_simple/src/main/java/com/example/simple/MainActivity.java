package com.example.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simple.api.HttpClient;
import com.example.simple.api.RetrofitManager;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void initView() {
        findViewById(R.id.rt_test1).setOnClickListener(this);
        findViewById(R.id.rt_test2).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rt_test1:
                login();
                break;
            case R.id.rt_test2:
                getPager();
                break;
        }
    }

    private void login() {

        HttpClient.login("13977777777","123456abc");
       // manager.
    }

    private void getPager() {

        HttpClient.getPager("HM097I7kPie3PLY4erPbX8v7Tto");
        // manager.
    }
}
