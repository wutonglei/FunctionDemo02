package com.wutong.functiondemo02.MyDemo0_20.My00ColdStartApp;

import android.app.Application;

/**
 * Created by jiuman on 2019/10/25.
 */

public class My00Application extends Application {
//10-25 18:07:27.369 1154-1179/? I/ActivityManager: Displayed com.wutong.functiondemo02/.MainActivity: +1s652ms
    @Override
    public void onCreate() {
        super.onCreate();
        //对第三Sdk 和其他一般的内容进行初始化
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
