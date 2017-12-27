package com.org.net.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by guozhk on 2017/12/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
   public final  String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }


    protected abstract int getLayoutId();

    protected abstract void  initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {

    }


    protected <T extends View> T queryViewById(int resId) {
        return (T) findViewById(resId);

    }

    protected <T extends View> T queryViewById(View parentView, int resId) {
        return (T) parentView.findViewById(resId);

    }

    protected <T extends View> T queryViewById(int viewId, boolean clickListener) {
        if (viewId > 0) {
            T view = (T) findViewById(viewId);
            if (clickListener && view != null)
                addOnClickListener(view);
            return view;
        }
        return null;
    }

    protected void addOnClickListener(View... views) {
        for (int i = 0; i < views.length; i++)
            views[i].setOnClickListener(this);
    }


}
