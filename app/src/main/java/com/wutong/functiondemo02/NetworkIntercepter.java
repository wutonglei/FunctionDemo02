package com.wutong.functiondemo02;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;


import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


import okio.Buffer;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Created by jiuman on 2019/11/18.
 */


public class NetworkIntercepter implements Interceptor {
    String TAG = "trh" + this.getClass().getSimpleName();

    //    private static Logger LOGGER = LoggerFactory.getLogger(NetworkIntercepter.class);
    @Override
    public Response intercept(Interceptor.Chain chain) {
        long start = System.currentTimeMillis();
        Response response = null;
        String responseBody = null;
        String responseCode = null;
        String url = null;
        String requestBody = null;
        try {
            Request request = chain.request();
            url = request.url().toString();
            requestBody = getRequestBody(request);
            response = chain.proceed(request);
            responseBody = response.body().string();
            responseCode = String.valueOf(response.code());
            MediaType mediaType = response.body().contentType();
            response = response.newBuilder().body(ResponseBody.create(mediaType, responseBody)).build();
        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
        } finally {
            long end = System.currentTimeMillis();
            String duration = String.valueOf(end - start);
            Log.i(TAG, "responseTime= {"+duration+"}");
            Log.i(TAG, "requestUrl= {"+url+"}");
            Log.i(TAG, "params= {"+requestBody+"}");
            Log.i(TAG, "responseCode= {"+responseCode+"}");
            Log.i(TAG, "result= {"+responseBody+"}");

        }

        return response;
    }

    private String getRequestBody(Request request) {
        String requestContent = "";
        if (request == null) {
            return requestContent;
        }
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return requestContent;
        }
        try {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("utf-8");
            requestContent = buffer.readString(charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestContent;
    }
}
