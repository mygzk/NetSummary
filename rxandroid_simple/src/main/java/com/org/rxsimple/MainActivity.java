package com.org.rxsimple;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.org.rxsimple.net.NetService;
import com.org.rxsimple.net.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        RetrofitManager.getInstanse().createService(NetService.class).
                login("13977777777", "123456abc")
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        Log.e("apply","adsdasdasda");
                        return responseBody.string();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
             /*   .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });*/
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("result","onSubscribe");

                    }

                    @Override
                    public void onNext(String s) {

                        Log.e("result","onNext s:"+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("result","onError");
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {
                        Log.e("result","onComplete");
                    }
                });


    }
}
