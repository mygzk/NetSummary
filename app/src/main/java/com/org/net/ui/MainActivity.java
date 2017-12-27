package com.org.net.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.org.net.Bean.ClassBean;
import com.org.net.R;
import com.org.net.adapter.MainAdapter;
import com.org.net.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView rcList;
    private MainAdapter mAdapter;
    private List<ClassBean> mData;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView() {
        rcList = (RecyclerView) findViewById(R.id.rc_list);
        initData();
        mAdapter = new MainAdapter(mData);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcList.setLayoutManager(manager);
        rcList.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));
        mData.add(new ClassBean(OrginActivity.class, "OrginActivity"));


    }
}
