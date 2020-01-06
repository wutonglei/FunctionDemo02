package com.wutong.functiondemo02.MyDemo0_20.My06Okhttputils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wutong.functiondemo02.utils.AppUtils;
import com.wutong.functiondemo02.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 需要完成以下任务
 * 1.下载文件 并保存到相应位置
 * <p>
 * 2.多任务下载  串行  并设置全部取消
 */
public class My06OkhttputilsActivity extends AppCompatActivity {

    String TAG = "trh" + this.getClass().getSimpleName();

    String videoFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + AppUtils.getAppPackageName() + "/cache";
    @BindView(R.id.btn_task1)
    Button btnTask1;
    @BindView(R.id.btn_task2)
    Button btnTask2;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttputils);
        ButterKnife.bind(this);
        String url = "https://api.9mankid.com/uploads/admin/media/video/35874bb6d5fec51e1a54d9fe6ce81e07.mp4";
        activity=this;
        task1(url);

        Log.i(TAG, "videoFilePath: " + videoFilePath);
        task2();


    }

    List<String> urls = new ArrayList<>();

    private void task2() {


        String url1 = "https://api.9mankid.com/uploads/admin/media/video/35874bb6d5fec51e1a54d9fe6ce81e07.mp4";
        String url2 = "https://api.9mankid.com/uploads/admin/media/video/aea4b2398bc7687ebceca05278d1ce12.mp4";
        String url3 = "https://api.9mankid.com/uploads/admin/media/video/a4a07bba0cf46da331b1fc8813714398.mp4";

        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        count = 0;
        task2x();

    }

    int count;

    private void task2x() {
        final int current = count;
        if (count < urls.size())
            OkHttpUtils//
                    .get()//
                    .url(urls.get(count))//
                    .tag(activity)
                    .build()//
                    .execute(new FileCallBack(videoFilePath, "xxx" + count + ".mp4")//
                    {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i(TAG, "当前：" + current + "onError: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            Log.i(TAG, "当前：" + current + "onResponse: "+response.getAbsolutePath());
                            Log.i(TAG, "onResponse: "+response.getName());
                            count++;
                            task2x();
                        }

                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            Log.i(TAG, "当前：" + current + " inProgress: " + progress);
                        }
                    });
    }


    private void task1(String url) {

        OkHttpUtils//
                .get()//
                .url(url)//
                .tag(activity)
                .build()//
                .execute(new FileCallBack(videoFilePath, "xxx.mp4")//
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.i(TAG, "onResponse: ");
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        Log.i(TAG, "inProgress: " + progress);
                    }
                });
    }

    @OnClick({R.id.btn_task1, R.id.btn_task2, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_task1:
                task1(urls.get(0));
                break;
            case R.id.btn_task2:
                task2();
                break;
            case R.id.btn_cancel:

                count=urls.size()+1;
                OkHttpUtils.getInstance().cancelTag(activity);

                break;
        }
    }
}
