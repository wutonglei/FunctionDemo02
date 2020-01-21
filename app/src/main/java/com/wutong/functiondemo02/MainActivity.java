package com.wutong.functiondemo02;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wutong.functiondemo02.MyDemo0_20.My01Annotation.My01AnnotationActivity;
import com.wutong.functiondemo02.MyDemo0_20.My02Fragment.My02FragmentActivity;
import com.wutong.functiondemo02.MyDemo0_20.My03SoundSize.My03SoundSizeActivity;
import com.wutong.functiondemo02.MyDemo0_20.My04MediaPlayer.My04MediaPlayerActivity;
import com.wutong.functiondemo02.MyDemo0_20.My05WebView.My05WebViewActivity;
import com.wutong.functiondemo02.MyDemo0_20.My06Okhttputils.My06OkhttputilsActivity;

import com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch.My07MutiTouchActivity;
import com.wutong.functiondemo02.MyDemo0_20.My08Record.My08RecordActivity;
import com.wutong.functiondemo02.MyDemo0_20.My09Service.My09ServiceActivity;
import com.wutong.functiondemo02.MyDemo0_20.My09Service.MyService;
import com.wutong.functiondemo02.MyDemo0_20.My10KeyBoard.My10KeyBoardActivity;
import com.wutong.functiondemo02.MyDemo0_20.My11Doodle.My11DoodleActivity;
import com.wutong.functiondemo02.MyDemo0_20.My12CaughtException.My12CaughtExceptionActivity;
import com.wutong.functiondemo02.MyDemo0_20.My13CheckLayout.My13CheckLayoutActivity;
import com.wutong.functiondemo02.MyDemo0_20.My14ShowSomePicture.My14ShowSomePictureActivity;
import com.wutong.functiondemo02.utils.EncryptUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    MyListViewAdapter adapter;
    Intent i;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.start_lv)
    ListView startLv;
    @BindView(R.id.btn_post_test)
    Button btnPostTest;
    @BindView(R.id.btn_get_test)
    Button btnGetTest;
    @BindView(R.id.btn_post_head)
    Button btnPostHead;
    @BindView(R.id.btn_load_head)
    Button btnLoadHead;
    @BindView(R.id.iv_head)
    ImageView ivHead;

    int count = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setBackgroundDrawable(null);
        ButterKnife.bind(this);

        Log.i(TAG, "onCreate: trh");
        Log.i(TAG, "onCreate: zxc");
        Log.i(TAG, "onCreate: xxxxxx");


        Log.e(TAG, "onCreate: trh");
        Log.e(TAG, "onCreate: zxc");
        Log.e(TAG, "onCreate: xxxxxx");


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                count++;
                Log.i(TAG, "run: " + count);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText("" + count);
                    }
                });

                if (count >= 10)
                    timer.cancel();
            }
        }, 1000, 1000);

        adapter = new MyListViewAdapter(this);
        startLv.setAdapter(adapter);
        startLv.setOnItemClickListener(this);
        //测试

        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);

        tvTime.setText(dateNowStr);
        List<XXXX> xxxxes = new ArrayList<>();
        for (int j = 0; j < 50; j++) {
            xxxxes.add(XXXX.getInstance(j, "sdaasd" + j));

            Log.i(TAG, "onCreate: size:" + xxxxes.size());

        }

        xxxxes.remove(XXXX.getInstance(10, "sdaasd"));
        Log.i(TAG, "onCreate: size:" + xxxxes.size());

        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                i = new Intent(MainActivity.this, My01AnnotationActivity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(MainActivity.this, My02FragmentActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(MainActivity.this, My03SoundSizeActivity.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(MainActivity.this, My04MediaPlayerActivity.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(MainActivity.this, My05WebViewActivity.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(MainActivity.this, My06OkhttputilsActivity.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(MainActivity.this, My07MutiTouchActivity.class);
                startActivity(i);
                break;
            case 7:
                i = new Intent(MainActivity.this, My08RecordActivity.class);
                startActivity(i);
                break;

            case 8:
                i = new Intent(MainActivity.this, My09ServiceActivity.class);
                startActivity(i);
                break;
            case 9:
                i = new Intent(MainActivity.this, My10KeyBoardActivity.class);
                startActivity(i);
                break;
            case 10:
                i = new Intent(MainActivity.this, My11DoodleActivity.class);
                startActivity(i);
                break;

            case 11:
                i = new Intent(MainActivity.this, My12CaughtExceptionActivity.class);
                startActivity(i);
                break;
      case 12:
                i = new Intent(MainActivity.this, My13CheckLayoutActivity.class);
                startActivity(i);
                break;
      case 13:
                i = new Intent(MainActivity.this, My14ShowSomePictureActivity.class);
                startActivity(i);
                break;

//                Intent intent;
//                intent = new Intent(this, MyService.class);
//                stopService(intent);




            /**
             * 16位 小写	7412b5da7be0cf42
             16位 大写	7412B5DA7BE0CF42
             32位 小写	fcea920f7412b5da7be0cf42b8c93759
             32位 大写	FCEA920F7412B5DA7BE0CF42B8C93759
             */
//        String url="https://api.9mankid.com/api/v1/login/login/";
//
//        OkHttpUtils
//                .post()
//                .url(url)
//                .addParams("phone", "15067103900")
//                .addParams("password", "fcea920f7412b5da7be0cf42b8c93759")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.i("trh", "onResponse: "+response);
//                    }
//                });
//                break;

        }
    }

    String authorization = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJQS0U1OEVRWSIsImlhdCI6MTU3NDMyNjgyMSwibmJmIjoxNTc0MzI2ODIxLCJleHAiOjE1NzQ5MzE2MjF9.IGjZ8tmc4pcbGuYiZwnq813KzyCoVhg5Nz7GsEJ_REs";

    @OnClick({R.id.btn_post_test, R.id.btn_get_test})
    public void onViewClicked(View view) {
        String url = "https://api.9mankid.com/api/v1/child/queryChild/";
        switch (view.getId()) {
            case R.id.btn_post_test:

//                url = "https://api.9mankid.com/api/v1/child/updateChild/";
                url = "https://api.9mankid.com/api/v1/message/sendSMSCode/";

                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("phone", "15067103900")
                        .addParams("id", "3")
                        .addParams("uname", "梧桐泪")
                        .addParams("sex", "2")
                        .addParams("birth", "2018-9-9")
                        .addParams("headimg", "datas/image/headimg/0449f18251c4a472cc5cf4d7bc44f625.png")
                        .addHeader("authorization", authorization)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("trh", "onResponse: " + response);
                            }
                        });
                break;
            case R.id.btn_get_test:

//                url = "https://api.9mankid.com/api/v1/classRoom/queryClassSchedule/";
                url = "https://api.9mankid.com/api/v1/child/queryChild/";
//                url = "https://api.9mankid.com/api/v1/courseware/queryCoursewareDetail/";

                OkHttpUtils
                        .get()
                        .url(url)
//                        .addParams("id", "2")
//                        .addParams("pageno", "0")
//                        .addParams("pagesize", "3")
                        .addHeader("authorization", authorization)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("trh", "onResponse: " + response);
                            }
                        });
                break;
        }
    }

    String TAG = "trh" + this.getClass().getSimpleName();

    @OnClick({R.id.btn_post_head, R.id.btn_load_head})
    public void onViewClicked2(View view) {
        String url = "https://api.9mankid.com/api/v1/child/queryChild/";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.png");
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        file.exists();
        Log.i(TAG, "onViewClicked2: " + file.getName() + "suffix:" + suffix);
        switch (view.getId()) {
            case R.id.btn_post_head:
                url = "https://api.9mankid.com/api/v1/child/uploadHeadimg/";
                String md5 = EncryptUtils.encryptMD5File2String(file);
                Log.i(TAG, "onViewClicked2: " + md5);
                OkHttpUtils
                        .post()
                        .url(url)
                        .addFile("file", md5 + ".png", file)
                        .addHeader("authorization", authorization)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i(TAG, "onResponse: " + response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    String filename = data.getString("data");
                                    Log.i(TAG, "onResponse: " + filename);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                break;
            case R.id.btn_load_head:
                url = "https://api.9mankid.com/uploads/" + "test.png";
//                Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.png"
                Glide.with(this).load("").into(ivHead);


                url = "https://api.9mankid.com/api/v1/classRoom/queryClassSchedule/";

                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("identity", "2")
                        .addParams("pageno", "0")
                        .addParams("pagesize", "1000")
                        .addHeader("authorization", authorization)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.i("trh", "onResponse: " + response);
                            }
                        });
                break;
        }
    }

    public static class XXXX {
        int x;
        String name;

        private static XXXX xxxx = null;

        public static XXXX getInstance(int x, String name) {
            if (xxxx == null) {
                xxxx = new XXXX(x, name);
            }
            xxxx.x = x;
            xxxx.name = name;

            return xxxx;
        }

        private XXXX(int x, String name) {
            this.x = x;
            this.name = name;
        }
    }
}
