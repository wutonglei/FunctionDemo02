package com.wutong.functiondemo02.MyDemo0_20.My00ColdStartApp;

import android.app.Application;

/**
 * Created by jiuman on 2019/10/25.
 */

public class My00Application2 extends Application {
//10-25 18:08:14.150 1154-1179/? I/ActivityManager: Displayed com.wutong.functiondemo02/.MainActivity: +649ms

    @Override
    public void onCreate() {
        super.onCreate();
        //对第三Sdk 和其他一般的内容进行初始化
        InitializeService.init(this);
    }
}
