package com.wutong.functiondemo02.MyDemo0_20.My04MediaPlayer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.wutong.functiondemo02.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My04MediaPlayerActivity extends AppCompatActivity {

    @BindView(R.id.surfaceView)
    MyMediaPlayerView surfaceView;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_pause)
    Button btnPause;
    @BindView(R.id.btn_switch)
    Button btnSwitch;
    @BindView(R.id.btn_seekto)
    Button btnSeekto;
    @BindView(R.id.sb)
    SeekBar sb;
    //读取本地文件
    private File file;
    String TAG = "trh" + this.getClass().getSimpleName();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                sendEmptyMessageDelayed(1, 1000);
                sb.setProgress(surfaceView.getCurrentPosition() * 100 / surfaceView.getDuration());
                Log.i(TAG, "handleMessage: Progress" + sb.getProgress());
                Log.i(TAG, "handleMessage:CurrentPosition " + surfaceView.getCurrentPosition());
                Log.i(TAG, "handleMessage: Duration" + surfaceView.getDuration());
                getSystemVolume();
                surfaceView.setVolume(1f, 1f);
            }
        }
    };
Context context;
    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my04_media_player);
        ButterKnife.bind(this);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/video2.mp4");
        Log.i(TAG, "onCreate: " + file.getAbsolutePath());
        surfaceView.setVideoPath(file.getAbsolutePath());
        mActivity=this;
        sb.setMax(100);
        context=this;

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 100) {
                    surfaceView.pause();
                    handler.removeMessages(1);
                    return;
                }
//试一试
                Log.i(TAG, "onProgressChanged: progress" + progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Log.i(TAG, "onProgressChanged: surfaceView.getDuration()" + surfaceView.getDuration());
                surfaceView.start();

                int seekTo = seekBar.getProgress() * surfaceView.getDuration() / 100;
                Log.i(TAG, "onProgressChanged: seekTo" + seekTo);
                surfaceView.seekTo(seekTo);


            }
        });
    }

    private void getSystemVolume() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        int max, current;
        max = am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);// 0
        current = am.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        Log.e("service", "通话音量值：" + max + "-" + current);


        max = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);// 1
        current = am.getStreamVolume(AudioManager.STREAM_SYSTEM);
        Log.e("service", "系统音量值：" + max + "-" + current);


        max = am.getStreamMaxVolume(AudioManager.STREAM_RING);// 2
        current = am.getStreamVolume(AudioManager.STREAM_RING);
        Log.e("service", "系统铃声值：" + max + "-" + current);


        max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 3
        current = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.e("service", "音乐音量值：" + max + "-" + current);


        max = am.getStreamMaxVolume(AudioManager.STREAM_ALARM);// 4
        current = am.getStreamVolume(AudioManager.STREAM_ALARM);
        Log.e("service", "闹铃音量值：" + max + "-" + current);


        max = am.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);// 5
        current = am
                .getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        Log.e("service", "提示声音音量值：" + max + "-" + current);
    }

    @OnClick({R.id.btn_start, R.id.btn_pause, R.id.btn_switch, R.id.btn_seekto, R.id.btn_get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                surfaceView.start();
                handler.sendEmptyMessage(1);

                break;
            case R.id.btn_pause:
                surfaceView.pause();
                handler.removeMessages(1);
                break;
            case R.id.btn_switch:
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xxx0.mp4");
                surfaceView.setVideoPath(file.getAbsolutePath());
                break;
            case R.id.btn_seekto:

                surfaceView.seekTo((int) (surfaceView.getDuration() * 0.5));
                break;
            case R.id.btn_get:
                syncVolume();
                AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                int mode = am.getMode();
                am.setMode(AudioManager.MODE_IN_CALL);
                am.setSpeakerphoneOn(true);
                int streamVolume = am.getStreamVolume(mode);
                Log.i(TAG, "onViewClicked: mode:"+mode+"streamVolume:"+streamVolume);
                Log.i(TAG, "onViewClicked: isSpeakerphoneOn"+  am.isSpeakerphoneOn());
                break;
        }
    }

    SeekBar skbVolume;//控制音量大小
    boolean isProcessing = true;//判断是否录放
    boolean isRecording = false;//判断是否录放
    static final int FREQUENCY = 44100;
    static final int CHANNELCONFIGURATION = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    static final int AUDIOENCODING = AudioFormat.ENCODING_PCM_16BIT;
    int recBufSize, playBufSize;
    AudioRecord audioRecord;
    AudioTrack audioTrack;


    public void syncVolume() {
        AudioTrack audioTrack;
        recBufSize = AudioRecord.getMinBufferSize(FREQUENCY, CHANNELCONFIGURATION, AUDIOENCODING);
        Log.e("", "recBufSize:" + recBufSize);
        //获得播放缓存大小
        playBufSize = AudioTrack.getMinBufferSize(FREQUENCY, CHANNELCONFIGURATION, AUDIOENCODING);

        //创建录音和播放实例
        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, FREQUENCY, CHANNELCONFIGURATION, AUDIOENCODING, playBufSize, AudioTrack.MODE_STREAM);
        audioTrack.setStereoVolume(0.9f,0.9f);//设置当前音量

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
