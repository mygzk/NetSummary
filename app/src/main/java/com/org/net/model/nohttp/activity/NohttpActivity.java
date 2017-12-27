package com.org.net.model.nohttp.activity;

import android.view.View;
import android.widget.TextView;

import com.org.net.R;
import com.org.net.base.BaseActivity;
import com.org.net.base.inteface.IBaseView;
import com.org.net.dialog.WaitDialog;
import com.org.net.model.nohttp.presenter.NohttpPresenter;


/***
 * 严格参考Nohttp 开源代码
 * 源码解读参考博客：http://blog.csdn.net/yanzhenjie1003/article/details/52413226
 * 参考地址 https://github.com/yanzhenjie/NoHttp.git
 *
 * 只有简单例子
 */
public class NohttpActivity extends BaseActivity implements IBaseView {

    private TextView tvResult;

    private NohttpPresenter mNohttpPresenter;
    private WaitDialog mWaitDialog;

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


    @Override
    public void start() {
        mWaitDialog = new WaitDialog(this);
        mWaitDialog.show();
    }

    @Override
    public void fail() {
        mWaitDialog.cancel();

    }

    @Override
    public void result(Object object) {
        mWaitDialog.cancel();
        tvResult.setText(object + "");
    }
}
