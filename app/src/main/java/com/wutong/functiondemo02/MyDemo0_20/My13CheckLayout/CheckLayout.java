package com.wutong.functiondemo02.MyDemo0_20.My13CheckLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiuman on 2020/1/8.
 */

public class CheckLayout extends RelativeLayout {
    private static final String TAG = "CheckLayout";
    List<Checkable> checkViewList=new ArrayList<>();
    boolean isCheck=false;
    public CheckLayout(Context context) {
        this(context,null);
    }

    public CheckLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CheckLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View view;
        for (int i = 0; i <getChildCount() ; i++) {
            view=getChildAt(i);
            if(view instanceof Checkable){
                checkViewList.add((Checkable) view);
                ((Checkable) view).setChecked(isCheck);
            }
        }
        setClickable(true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck=!isCheck;
                Log.i(TAG, "onClick:ViewGroup被点击");
                for (Checkable c : checkViewList) {
                    c.setChecked(isCheck);
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                isCheck=!isCheck;
                Log.i(TAG, "onClick:ViewGroup被点击");
                for (Checkable c : checkViewList) {
                    c.setChecked(isCheck);
                }
                break;

        }
        return true;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


}
