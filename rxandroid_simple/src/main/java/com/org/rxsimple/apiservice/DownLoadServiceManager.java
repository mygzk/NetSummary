package com.org.rxsimple.apiservice;

import com.org.rxsimple.LoginEnty;
import com.org.rxsimple.net.ApiSubscribe;
import com.org.rxsimple.net.BaseEntity;
import com.org.rxsimple.net.DefaultTransformer;
import com.org.rxsimple.net.NetCallback;
import com.org.rxsimple.net.down.DownLoadManager;
import com.org.rxsimple.net.down.IProcessListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;

/**
 * Create by guozhk
 * Date : 2018/11/20
 * Dre :
 **/
public class DownLoadServiceManager {


    private DownloadService mDownloadService;

    public static class DownLoadServiceManagerInstance {
        private static DownLoadServiceManager INSTANCE = new DownLoadServiceManager();
    }

    private DownLoadServiceManager() {
        mDownloadService = DownLoadManager.getInstanse().createService(DownloadService.class);
    }

    public static DownLoadServiceManager getInstance() {
        return DownLoadServiceManagerInstance.INSTANCE;
    }


    public void login(String url, final IProcessListener listener) {
        mDownloadService.download(url)
                .compose(new DefaultTransformer<ResponseBody>())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (listener != null) {
                            listener.onStart();
                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (responseBody == null) {
                            writeFile(responseBody.byteStream(), "", listener);
                        } else {
                            if (listener != null) {
                                listener.onFail("下载失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFail(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        listener.onFinishDownload();
                    }
                });
    }


    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    private void writeFile(InputStream inputString, String filePath, IProcessListener listener) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            inputString.close();
            fos.close();

        } catch (FileNotFoundException e) {
            listener.onFail("FileNotFoundException");
        } catch (IOException e) {
            listener.onFail("IOException");
        }

    }


}
