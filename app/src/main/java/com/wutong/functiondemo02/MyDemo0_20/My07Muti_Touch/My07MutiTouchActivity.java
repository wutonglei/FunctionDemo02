package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.wutong.functiondemo02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  写一个demo 改变角度 获取当前点的坐标
 *
 * 感觉自己写着急了 不应该一上来就搞无限点的
 *
 * 1.移动
 * 2.旋转
 * 3.缩放
 * 4.滑动方向
 * <p>
 * 5.多个复合+ 点击独立判断
 */
public class My07MutiTouchActivity extends AppCompatActivity {
String TAG="trh"+this.getClass().getSimpleName();
    @BindView(R.id.testv)
    My07_06Muti_Zoom testv;
    @BindView(R.id.btn_get)
    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my07_muti_touch);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        Log.i(TAG, "onViewClicked: "+testv.getRotation());
    }
}
