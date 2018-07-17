package com.example.simple.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.simple.MD5Util;
import com.example.simple.RetrofitApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guozhk on 2018/4/17.
 */

public class HttpClient {

    public static <T> void login(String phone, String pwd) {

        Map<String, String> header = new HashMap<>();
        header.put("security", getSecurity());
        header.put("appVersion", "1.3.2");
        header.put("os", "android");


        // Call<ResponseBody> call = RetrofitManager.getInstanse().getHttpService().login(header, phone, MD5Util.string2MD5(pwd + "dahuo"));
        Call<ResponseBody> call = RetrofitManager.getInstanse().getHttpService().login(phone,
                MD5Util.string2MD5(pwd + "dahuo"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println("response:" + response.body().string());
                    String s = response.body().string();
                    Log.e("res","response:" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                System.out.println("onFailure:" + t.getMessage());
            }
        });


    }

    public static void getInfo() {
        Call<ResponseBody> call = RetrofitManager.getInstanse().getHttpService().gettest();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response != null && response.body() != null) {
                        System.out.println("response:" + response.body().string());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                System.out.println("onFailure:" + t.getMessage());
            }
        });
    }


    @SuppressLint("MissingPermission")
    private static String getSecurity() {
        try {
            TelephonyManager tel = (TelephonyManager) RetrofitApp.mApp
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return tel.getDeviceId();
        } catch (Exception ex) {
            return "";
        }
    }



    public static void getPager(String token){
        Call<ResponseBody> call = RetrofitManager.getInstanse().getHttpService().getPapers(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response != null && response.body() != null) {
                        System.out.println("response:" + response.body().string());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                System.out.println("onFailure:" + t.getMessage());
            }
        });
    }


    public static <T> void sendRequet(Call<T> call ){

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
//                try {
//                    if (response != null && response.body() != null) {
//                        System.out.println("response:" + response.body().string());
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
//                t.printStackTrace();
//                System.out.println("onFailure:" + t.getMessage());
            }
        });

    }

}
