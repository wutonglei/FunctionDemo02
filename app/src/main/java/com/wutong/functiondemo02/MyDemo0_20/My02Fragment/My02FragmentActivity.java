package com.wutong.functiondemo02.MyDemo0_20.My02Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wutong.functiondemo02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 优先实现的功能
 * 1.fragment 的隐藏 和显示
 */
public class My02FragmentActivity extends AppCompatActivity  implements My02Fragment.OnFragmentInteractionListener{

    @BindView(R.id.btn_load_hide_show)
    Button btnLoadHideShow;
    @BindView(R.id.fl)
    FrameLayout fl;
    Fragment fragment;
    My02Fragment my02Fragment;

    boolean isShow=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my02_fragment);
        ButterKnife.bind(this);
        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();

        my02Fragment = (My02Fragment) getSupportFragmentManager().findFragmentByTag("f0");
        if(my02Fragment==null){
            my02Fragment=My02Fragment.newInstance(null,null);
        }
        transaction.add(R.id.fl,my02Fragment,"f0");
        transaction.show(my02Fragment).commit();

    }

    @OnClick(R.id.btn_load_hide_show)
    public void onViewClicked() {
        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction();

        if(isShow){
            transaction.hide(my02Fragment).commit();

        }else {
            transaction.show(my02Fragment).commit();
        }
        isShow=!isShow;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
