package com.wutong.functiondemo02.MyDemo0_20.My14ShowSomePicture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wutong.functiondemo02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My14ShowSomePictureActivity extends AppCompatActivity {

    @BindView(R.id.miv)
    MyImageView miv;
    @BindView(R.id.btn_mode1)
    Button btnMode1;
    @BindView(R.id.btn_mode2)
    Button btnMode2;
    @BindView(R.id.btn_mode3)
    Button btnMode3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my14_show_some_picture);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_mode1, R.id.btn_mode2, R.id.btn_mode3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_mode1:
                miv.setMode(MyImageView.MODE_1);
                break;
            case R.id.btn_mode2:
                miv.setMode(MyImageView.MODE_2);
                break;
            case R.id.btn_mode3:
                miv.setMode(MyImageView.MODE_3);
                break;
        }
    }
}
