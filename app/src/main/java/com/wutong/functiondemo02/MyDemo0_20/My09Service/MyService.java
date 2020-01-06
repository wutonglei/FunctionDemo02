package com.wutong.functiondemo02.MyDemo0_20.My09Service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wutong.functiondemo02.utils.AppUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * 1.验证startService 可以独立于 程序运行
 * 2.stopSelf 在线程中会是怎么的效果
 */
public class MyService extends Service {
    String TAG = "trh" + this.getClass().getSimpleName();

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private MyBinder binder = new MyBinder();

    private Listener listener;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.


        return binder;
    }

    public interface Listener {
        void downloadListener(int progress);
    }

    public void startRecord(){

    }
     public void stopRecord(){

    }

    public void cancelDownload(){
        listener=null;
        OkHttpUtils.getInstance().cancelTag(this);
    }

    public void download(@Nullable Listener listener) {
        String videoFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + AppUtils.getAppPackageName() + "/cache";
        String url="https://api.9mankid.com/uploads/admin/media/video/35874bb6d5fec51e1a54d9fe6ce81e07.mp4";
        int current=0;
        OkHttpUtils//
                .get()//
                .url(url)//
                .tag(this)
                .build()//                                        注意只有成功下载才改名字 不然还是按现在的名字来
                .execute(new FileCallBack(videoFilePath, "xxx"  + ".mp4")//
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "当前：" + current + "onError: " + e.getMessage());

                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.i(TAG, "当前：" + current + "onResponse: " + response.getAbsolutePath());
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        if (listener != null) {
                            listener.downloadListener((int) progress);
                        }
                        Log.i(TAG, "当前：" + current + " inProgress: " + progress+"  total"+total +"  比例："+progress/total);
                    }
                });
    }


    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");

    }

    int id = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand:这里获取传入参数 ");


        new Thread(new Runnable() {
            @Override
            public void run() {

                int x=0;
                while (startId != id && id != -1) {
                    x++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, Thread.currentThread().getName() + x+" run: id:" + id + "  startId:" + startId);
                    if(x>20){
                        stopSelf(startId);

                        Thread.currentThread().interrupt();//讲道理这玩意儿有点都没用
                    }

                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);

    }
//鬼知道这个有什么用
    @Override
    public boolean stopService(Intent name) {
        Log.i(TAG, "stopService: ");
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        id=-1;

    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        Log.i(TAG, "bindService: ");
        
        return super.bindService(service, conn, flags);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }
}
