package com.wutong.functiondemo02.MyDemo0_20.My00ColdStartApp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jiuman on 2019/10/25.
 */

public class InitializeService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.sanxin.ttreader";

    public InitializeService() {
        super("InitializeService");
    }

    public static void init(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    /**.
     * do you init action here
     * 这里做你的Application的初始化工作
     * 值得注意的是如果第一个Activity直接用到这里面的东西我怀疑可能会出事
     */
    private void performInit() {
        //do somthing youself here
        try {
            Log.i("trh", "performInit: 我想看看有没有执行");
            Thread.sleep(1000);
            Log.i("trh", "performInit: 然后就结束了我笑了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}