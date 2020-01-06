package com.wutong.functiondemo02;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.wutong.functiondemo02.MyDemo0_20.My08Record.RecordApplication;
import com.wutong.functiondemo02.MyDemo0_20.My08Record.RecordService;
import com.wutong.functiondemo02.MyDemo0_20.My12CaughtException.CrashHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jiuman on 2019/11/12.
 */

public class MyApplication extends Application {
    private static   MyApplication instance;
    private static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
    }

    @Override
    public void onCreate() {
        setTheme(R.style.AppTheme);
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());


        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(logInterceptor)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        startService(new Intent(this, RecordService.class));
    }
}
