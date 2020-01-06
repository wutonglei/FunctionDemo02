package com.wutong.functiondemo02;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jiuman on 2019/11/18.
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("trh", message);
    }
}
