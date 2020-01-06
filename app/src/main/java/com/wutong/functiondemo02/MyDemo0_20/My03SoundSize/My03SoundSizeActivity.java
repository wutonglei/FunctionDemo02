package com.wutong.functiondemo02.MyDemo0_20.My03SoundSize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wutong.functiondemo02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My03SoundSizeActivity extends AppCompatActivity {

    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_star)
    Button btnStar;
    AudioUtils audioUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my03_sound_size);
        ButterKnife.bind(this);


    }



    @OnClick({R.id.btn_stop, R.id.btn_star})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_stop:
                if (audioUtils!=null){
                    audioUtils.stop();
                }
                break;
            case R.id.btn_star:
                audioUtils = new AudioUtils();
                audioUtils.getNoiseLevel();

                break;
        }
    }
}
