package com.wutong.functiondemo02.MyDemo0_20.My09Service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wutong.functiondemo02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My09ServiceActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.tv_current_progress)
    TextView tvCurrentProgress;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.btn_bind)
    Button btnBind;
    @BindView(R.id.btn_undbind)
    Button btnUndbind;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvCurrentProgress.setText("当前进度：" + msg.what);
        }
    };
    boolean isBind = false;
    MyService service;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

            isBind = true;
            MyService.MyBinder myBinder = (MyService.MyBinder) binder;
            service = myBinder.getService();
            Log.i("Kathy", "ActivityA - onServiceConnected");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBind = false;
            Log.i("Kathy", "ActivityA - onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my09_service);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_bind, R.id.btn_undbind, R.id.btn_download, R.id.btn_record})
    public void onViewClicked(View view) {
        Intent intent;
        intent = new Intent(this, MyService.class);

        switch (view.getId()) {
            case R.id.btn_start:
                startService(intent);
                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
            case R.id.btn_bind:
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_undbind:
                service.cancelDownload();
                unbindService(conn);
                break;
            case R.id.btn_download:
                if (service != null) {
                    service.download(new MyService.Listener() {
                        @Override
                        public void downloadListener(int progress) {
                            handler.sendEmptyMessage(progress);
                        }
                    });
                }
                break;
            case R.id.btn_record:
                if (service != null) {
                    service.startRecord();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind)
            unbindService(conn);
        service = null;
    }
}
