package com.wutong.functiondemo02.MyDemo0_20.My05WebView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.wutong.functiondemo02.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1.预加载
 * 2.本地缓存的清理
 */
public class My05WebViewActivity extends AppCompatActivity {
    String TAG = "trh";
    String BASE_FILE_URL = "https://api.9mankid.com/uploads/";
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.btn_pre_load)
    Button btnPreLoad;
    @BindView(R.id.btn_clear_cache)
    Button btnClearCache;
    @BindView(R.id.btn_load)
    Button btnLoad;

    List<String> urls = new ArrayList<>();

    WebViewUtil myWebView;
    WebViewUtil myWebViewCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my05_web_view);
        ButterKnife.bind(this);
//        urls.add("https://api.9mankid.com/uploads/admin/file/cw/start.html?path=4d83bb209adc93230b2aaf140c6446f2&roomId=3648&peerId=2569&manager=1");
        urls.add("https://www.9man.com/study/home");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=4d83bb209adc93230b2aaf140c6446f2");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=0e746855c7b7af8e13c0cec84a0e3b25");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=b38e7945e67b0c976ea1adafec0070f3");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=645a19d69b38717e40912c87f77e31cc");

        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=4d83bb209adc93230b2aaf140c6446f2");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=0e746855c7b7af8e13c0cec84a0e3b25");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=b38e7945e67b0c976ea1adafec0070f3");
        urls.add(BASE_FILE_URL + "admin/file/cw/start.html?path=645a19d69b38717e40912c87f77e31cc");

        myWebViewCache = new WebViewUtil(this);
//        myWebViewCache.setCache();


        myWebView = new WebViewUtil(this);
        ////        myWebView.setProgress(pb);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myWebView.intoView(rl, params);
        myWebView.setCache();
//
//
//
//        btnClearCache.startAnimation();
//View view;
//view.setAnimation();

    }

    int count = 0;

    @OnClick({R.id.btn_pre_load, R.id.btn_clear_cache, R.id.btn_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pre_load:
                myWebViewCache.preLoadUrl(urls.get(0));
                break;
            case R.id.btn_clear_cache:
                myWebView.clearWebViewCache();
                break;
            case R.id.btn_load:
//               if(count>urls.size()){
//                   Log.i(TAG, "onViewClicked: 已经没有了");
//                   return;
//               }

                myWebView.loadUrl(urls.get(0));
                String str = "https://api.9mankid.com/uploads/admin/file/cw/start.html?path=004c743b48addccb003cd61dfc3487f0&roomId=mry79me13q&peerId=2GW8Z7DO&manager=1";
//                String str = "http://v.9man.com/s/?i=4805307";
//                str="https://liulanmi.com/labs/core.html";
                myWebView.loadUrl(str);
                count++;
                setClipboard(str);
                break;
        }
    }

    public void setClipboard(String str) {
//获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", str);
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }
}
