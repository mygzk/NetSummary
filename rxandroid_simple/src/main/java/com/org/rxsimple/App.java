package com.org.rxsimple;

import android.app.Application;

public class App extends Application {
   private static App mApp;
    @Override
    public void onCreate() {
        mApp = this;
        super.onCreate();
    }

    public static App getApp(){
        return mApp;
    }
}
