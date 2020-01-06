package com.wutong.functiondemo02;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Iterator;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
          final String TAG = "trh";
        assertEquals("com.wutong.functiondemo02", appContext.getPackageName());


        String s = "{\n" +
                "    \"com.pm.zse\": {\n" +
                "        \"check\": false,\n" +
                "        \"own\": true,\n" +
                "        \"complete\": true\n" +
                "    },\n" +
                "    \"com.om.cme\": {\n" +
                "        \"loaded\": false,\n" +
                "        \"complete\": false,\n" +
                "        \"com\": \"whoami\",\n" +
                "        \"aa\": [\n" +
                "            1,\n" +
                "            2,\n" +
                "            3\n" +
                "        ],\n" +
                "        \"cc\": {\n" +
                "            \"a\": 123,\n" +
                "            \"b\": 111\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Log.i(TAG, "useAppContext: "+s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            Iterator<String> objs =  jsonObject.keys();
            String key;
            while (objs.hasNext()){
                key = objs.next();
                Log.i(TAG, "key: "+key);
                JSONObject jo = jsonObject.getJSONObject(key);
                Iterator<String> ks =  jo.keys();
                String k;
                while (ks.hasNext()){
                    k = ks.next();
                    String v = jo.getString(k);
                    Log.i(TAG, "k: "+k);
                    Log.i(TAG, "v: "+v);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }







        String json="{\n" +
                "    \"success\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"上传成功\",\n" +
                "    \"data\": {\"data\": \"datas/image/headimg/0449f18251c4a472cc5cf4d7bc44f625.png\"}\n" +
                "}";
        JSONObject root = new JSONObject(json);
        JSONObject data = root.getJSONObject("data");
        String path = data.getString("data");

        Log.i(TAG, "useAppContext: path"+path);







         json="{\n" +
                "    \"success\": true,\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"上传成功\",\n" +
                "    \"list\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        3\n" +
                "    ],\n" +
                "    \"data\": {\"data\": \"datas/image/headimg/0449f18251c4a472cc5cf4d7bc44f625.png\"}\n" +
                "}";
        System.out.println("sdadasdsa123");
        Log.i(TAG, "useAppContext: ");

        JSONObject jsonObject=new JSONObject(json);
        Log.i(TAG, "useAppContext: "+jsonObject.getString("msg"));
        Log.i(TAG, "useAppContext: "+jsonObject.getInt("code"));
        Log.i(TAG, "useAppContext: "+jsonObject.getBoolean("success"));
        Log.i(TAG, "useAppContext: "+jsonObject.getBoolean("success"));
        JSONArray array= jsonObject.getJSONArray("list");

        for (int i = 0; i < array.length(); i++) {
            Log.i(TAG, "useAppContext: "+array.get(i));

        }
        JSONObject jsonObject1=jsonObject.getJSONObject("data");
        Log.i(TAG, "useAppContext: "+jsonObject1.getString("data"));
        System.out.println(jsonObject1.getString("data"));
    }
}
