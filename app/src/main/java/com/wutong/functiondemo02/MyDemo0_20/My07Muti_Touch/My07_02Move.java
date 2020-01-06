package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by jiuman on 2019/12/9.
 */

public class My07_02Move extends RelativeLayout {
    String TAG = "trh" + this.getClass().getSimpleName();
    public My07_02Move(Context context) {
        this(context, null);
    }

    public My07_02Move(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * onSingleTapConfirmed 和  onDoubleTap  可以区别单击和双击
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public My07_02Move(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    float preX,preY;
    float currentX,currentY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX=event.getX();
                preY=event.getY();
                Log.i(TAG, "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                currentX=event.getX();
                currentY=event.getY();
                Log.i(TAG, "onTouchEvent:     preX:"+preX+"  preY:"+preY);
                Log.i(TAG, "onTouchEvent: currentX:"+currentX+" currentY:"+currentY);

                setX(getX()+currentX-preX);
                setY(getY()+currentY-preY);
                currentX=preX;
                currentY=preY;
                Log.i(TAG, "onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                currentX=event.getX();
                currentY=event.getY();

                setX(getX()+currentX-preX);
                setY(getY()+currentY-preY);
                currentX=preX;
                currentY=preY;
                Log.i(TAG, "onTouchEvent: ACTION_UP");
                break;
        }
//        注意这里一定要消费掉 （主要是消费掉Down 不然后续的内容不会被执行） 不然后续没法搞
        return true;
    }
}
