package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * 然后 多点绘制
 * 2点触碰 ：
 * 缩放   俩点的距离来判断
 * 旋转   连接两点 算三角形的角度
 * 多点触碰:
 * 缩放      取全部点的平均值（重心）
 * 旋转      我认为 另外俩点连线的中点 和另一个 点来计算角度
 * <p>
 * 所以我这边也只考虑三指放大 （其实没必要）
 * Created by jiuman on 2019/12/9.
 */

public class My07_03Muti_Draw extends RelativeLayout {
    String TAG = "trh" + this.getClass().getSimpleName();

    public My07_03Muti_Draw(Context context) {
        this(context, null);
    }

    public My07_03Muti_Draw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * onSingleTapConfirmed 和  onDoubleTap  可以区别单击和双击
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    Paint mDefaultPaint;

    public My07_03Muti_Draw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setTextAlign(Paint.Align.CENTER);
        mDefaultPaint.setColor(Color.RED);
        mDefaultPaint.setStrokeWidth(5);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
    /**
     * 但是 move 事件始终为 0x00000002，也就是说，
     * 在 move 时不论你移动哪个手指，使用 getActionIndex() 获取到的始终是数值 0。
     * 区分Move pointId
     *
     * @param event
     * @return
     */
    //  pointerId
    Map<Integer, PointHolder> prePointHolderMap = new HashMap<>();
    Map<Integer, Path> pathHashMap = new HashMap<>();

//2指 一个抬起另一个就一定变为主手指(响应的也是ACTION_UP) 但是 getPointerId不会变

    //先简单的来一手双指 缩放
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentIndex = event.getActionIndex();
//        Log.i(TAG, "onTouchEvent:currentIndex "+currentIndex);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                down(event, currentIndex);

                break;
            case MotionEvent.ACTION_UP:
                prePointHolderMap.remove(event.getPointerId(currentIndex));
                Log.i(TAG, "ACTION_UP: ");
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pointerId = event.getPointerId(i);
                    //用于getX(pointerIndex)
                    int pointerIndex = event.findPointerIndex(pointerId);

                    PointHolder pointHolder = prePointHolderMap.get(pointerId);
                    pointHolder.x = event.getX(pointerIndex);
                    pointHolder.y = event.getY(pointerIndex);
                    pathHashMap.get(pointerId).lineTo(pointHolder.x, pointHolder.y);

                }
//                for (int i = 0; i < event.getPointerCount(); i++) {
//                    Log.i(TAG, "ACTION_MOVE: " + event.getPointerId(i));
//                }
//                if (haveSecondPoint) {
//                    // 通过 pointerId 来获取 pointerIndex
//                    int pointerIndex = event.findPointerIndex(1);
//                    // 通过 pointerIndex 来取出对应的坐标
//                    point.set(event.getX(pointerIndex), event.getY(pointerIndex));
//                }

                break;


            case MotionEvent.ACTION_POINTER_DOWN:
                down(event, currentIndex);


//                Log.i(TAG, "ACTION_POINTER_DOWN: 当前按下的总数"+event.getPointerCount());
//                Log.i(TAG, "ACTION_POINTER_DOWN: 我认为这是放最后的"+event.getPointerId(event.getPointerCount()-1));
//                Log.i(TAG, "ACTION_POINTER_DOWN: " + event.getPointerId(amountPoint++));
//                if (event.getPointerId(currentIndex) == 1){
//                    haveSecondPoint = true;
//                    point.set(event.getY(), event.getX());
//                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "第" + (currentIndex + 1) + "个手指抬起,其id为：" + event.getPointerId(currentIndex));
                prePointHolderMap.remove(event.getPointerId(currentIndex));


//                prePointHolderMap.remove()
//                survivalPoint--;
//                amountPoint--;
//
//                if (event.getPointerId(currentIndex) == 1){
//                    haveSecondPoint = false;
//                    point.set(0, 0);
//                }
                break;
        }
        invalidate();
//        注意这里一定要消费掉 （主要是消费掉Down 不然后续的内容不会被执行） 不然后续没法搞
        return true;
    }

    private void down(MotionEvent event, int currentIndex) {
        int pointerId = event.getPointerId(currentIndex);
        int pointerIndex = event.findPointerIndex(pointerId);
        PointHolder pointHolder = new PointHolder(event.getX(pointerIndex), event.getY(pointerIndex));
        prePointHolderMap.put(pointerId, pointHolder);
        Path path = new Path();
        path.moveTo(event.getX(pointerIndex), event.getY(pointerIndex));
        pathHashMap.put(event.getPointerId(currentIndex), path);
        Log.i(TAG, "第" + (currentIndex + 1) + "个手指按下,其id为：" + event.getPointerId(currentIndex));
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (Map.Entry<Integer,Path> entry : pathHashMap.entrySet()) {
            Integer mapKey = entry.getKey();
            Path path = entry.getValue();
            canvas.drawPath(path,mDefaultPaint);

        }
//        canvas.drawPath();
        // 如果屏幕上有第2个手指则绘制出来其位置

    }

    class PointHolder {
        //只有当Up状态才能把数据初始化 不然就是原来的手指还在继续中

        public float x;
        public float y;

        public PointHolder(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }


}
