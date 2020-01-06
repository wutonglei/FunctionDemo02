package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by jiuman on 2019/12/9.
 */

public class My07_01JudgeDirection extends RelativeLayout {
    String TAG = "trh" + this.getClass().getSimpleName();

    public My07_01JudgeDirection(Context context) {
        this(context, null);
    }

    public My07_01JudgeDirection(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * onSingleTapConfirmed 和  onDoubleTap  可以区别单击和双击
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public My07_01JudgeDirection(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {


                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            /**
             * velocity 的正负值 和坐标一致
             * @param e1
             * @param e2
             * @param velocityX
             * @param velocityY
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i(TAG, "onFling:检测滑动方向 velocityX+" + velocityX + "  velocityY:" + velocityY);
                judgeDirection(velocityX, velocityY);

                return super.onFling(e1, e2, velocityX, velocityY);
            }

            /**
             *
             * @param e
             */
            @Override
            public void onShowPress(MotionEvent e) {
                super.onShowPress(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.i(TAG, "onDoubleTap: 被双击了");
                Log.i(TAG, "第二次按下时触发");
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
//              e  switch (e.getActionMasked()) {
//                    case MotionEvent.ACTION_UP:
//                        Logger.e("第二次抬起时触发");
//                        break;
                Log.i(TAG, "第二次抬起时触发");
                return super.onDoubleTapEvent(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.i(TAG, "onSingleTapConfirmed: 被单击了");
                return super.onSingleTapConfirmed(e);
            }
        };

        gestureDetector = new GestureDetector(context, simpleOnGestureListener);
    }

    private void judgeDirection(float velocityX, float velocityY) {
        boolean isVertical = true;
        if (Math.abs(velocityX) > Math.abs(velocityY))
            isVertical = false;

        if (isVertical) {
            if (velocityY > 0) {
                Log.i(TAG, "judgeDirection: 向下滑动");
            } else {
                Log.i(TAG, "judgeDirection: 向上滑动");
            }
        } else {
            if (velocityX > 0) {
                Log.i(TAG, "judgeDirection: 向右滑动");
            } else {
                Log.i(TAG, "judgeDirection: 向左滑动");
            }
        }
    }

    GestureDetector gestureDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }

        gestureDetector.onTouchEvent(event);
        return true;
    }

    //不要复写了
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 拦截往下的走的事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


}
