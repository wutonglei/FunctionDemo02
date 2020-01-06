package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.wutong.functiondemo02.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;
//
//12-10 12:22:12.309 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:356.0895getY:226.6117   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.327 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:644.5402getY:28.395935   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.327 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:644.5402getY:28.395935   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.361 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:355.38943getY:226.93115   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.361 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:355.38943getY:226.93115   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.378 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:646.4359getY:25.587769   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.378 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:646.4359getY:25.587769   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.409 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:354.69446getY:227.25299   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.409 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:354.69446getY:227.25299   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.427 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:648.34503getY:22.74524   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.427 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:648.34503getY:22.74524   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.455 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:354.00482getY:227.57703   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.455 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:354.00482getY:227.57703   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.545 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:0onTouchEvent: getX:650.2676getY:19.867859   event.getRawX():244.0event.getRawY():844.0
//        12-10 12:22:12.546 5258-5258/com.wutong.functiondemo02 I/trhMy07_04Muti_Rotate_Zoom: all:1onTouchEvent: getX:650.2676getY:19.867859   event.getRawX():244.0event.getRawY():844.0

/**
 * 旋转和缩放的震动问题  于上所示
 * 原因分析 ：双指放着不动造成 get获取的值有较大的变化 估计是  get的算法里面是包含角度和缩放得到的值 最后造成反馈   从而使坐标一直在变动
 * 解决方式 ：  getRaw的方式获取值就ok了
 * 实际上  getRaw(int index )并不能用 应该是有办法的但是我不会
 * 所以这边我只能说 坐标点也都跟着变好了
 * 然后我想到了 getRaw 可以获取第一个点的值  那么通过这层关系 计算其他点的绝对位置
 * 就算绝对值 还是问题很多 我决定  缩放单独提取出来写
 * 但是现在写的是错误的  并不是绝对位置的值
 * <p>
 * 但是将真的如果 旋转采用dr的方式 缩放采用当前与重心的位置差值来比较应该是不需要绝对值的
 * 下面进行尝试
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 遗留问题
 * 旋转算法有问题
 * <p>
 * bug   null的  整理下
 * <p>
 * <p>
 * 2点触碰 ：
 * 缩放   俩点的距离来判断
 * 旋转   连接两点 算三角形的角度
 * 多点触碰:
 * 缩放      取全部点的平均值（重心）
 * 旋转      我认为 另外俩点连线的中点 和另一个 点来计算角度   多个点也一样   已经想通了
 * <p>
 * todo 2019-12-10 17:30:16 未写 旋转可能性 拿前一个点和当前点计算旋转角度 取所有旋转的平均值
 * 算了果断采用dr的方式来计算旋转角度好了
 * <p>
 * <p>
 * 所以我这边也只考虑三指放大 （其实没必要）
 * Created by jiuman on 2019/12/9.
 * <p>
 * 思路 每次MotionEvent.ACTION_POINTER_DOWN  重新计算初始 重心
 */

public class My07_05Muti_Rotate_Zoom_getRaw extends RelativeLayout {
    float rotation;
    float degree = 0;

    String TAG = "trh" + this.getClass().getSimpleName();

    public My07_05Muti_Rotate_Zoom_getRaw(Context context) {
        this(context, null);
    }

    public My07_05Muti_Rotate_Zoom_getRaw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * onSingleTapConfirmed 和  onDoubleTap  可以区别单击和双击
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public My07_05Muti_Rotate_Zoom_getRaw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

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


//2指 一个抬起另一个就一定变为主手指(响应的也是ACTION_UP) 但是 getPointerId不会变


    //先简单的来一手双指 缩放
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentIndex = event.getActionIndex();
//        Log.i(TAG, "onTouchEvent:currentIndex "+currentIndex);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                down(event, currentIndex);
                rotation = 0;
                break;
            case MotionEvent.ACTION_UP:
                prePointHolderMap.remove(event.getPointerId(currentIndex));
                Log.i(TAG, "ACTION_UP: ");

                break;

            case MotionEvent.ACTION_MOVE:

                int nullCount = 0;
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pointerId = event.getPointerId(i);
                    //用于getX(pointerIndex)
                    int pointerIndex = event.findPointerIndex(pointerId);
                    PointHolder pointHolderD = prePointHolderMap.get(pointerId);
                    pointHolderD.x = event.getX(pointerIndex);
                    pointHolderD.y = event.getY(pointerIndex);
                    PointHolder pointHolder = prePointHolderMap.get(i);

                    if (pointHolder == null) {
                        continue;
                    }


                }

                break;


            case MotionEvent.ACTION_POINTER_DOWN:
                down(event, currentIndex);

                initDegree(event);


                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "第" + (currentIndex + 1) + "个手指抬起,其id为：" + event.getPointerId(currentIndex));
                prePointHolderMap.remove(event.getPointerId(currentIndex));
                initDegree(event);

                break;
        }
//        invalidate();
//        注意这里一定要消费掉 （主要是消费掉Down 不然后续的内容不会被执行） 不然后续没法搞
        return true;
    }

    private void initDegree(MotionEvent event) {
        degree = 0;
        PointHolder mainPointHolder=null;
        int current=0;
        for (int j = 0; j < event.getPointerCount(); j++) {
            mainPointHolder = prePointHolderMap.get(j);
            if (mainPointHolder != null){
                current=j;
                break;
            }

        }
        if(mainPointHolder!=null)
        for (int i = current+1; i < event.getPointerCount(); i++) {

            PointHolder pointHolder = prePointHolderMap.get(i);

            if (pointHolder == null) {
                continue;
            }
            degree += getDegree(mainPointHolder,pointHolder);

        }
    }


    private void down(MotionEvent event, int currentIndex) {
        int pointerId = event.getPointerId(currentIndex);
        int pointerIndex = event.findPointerIndex(pointerId);

        PointHolder pointHolder = new PointHolder(event.getX(pointerIndex), event.getY(pointerIndex));
        prePointHolderMap.put(pointerId, pointHolder);

        for (int i = 0; i < event.getPointerCount(); i++) {
            int pointerIdD = event.getPointerId(i);
            //用于getX(pointerIndex)
            int pointerIndexD = event.findPointerIndex(pointerIdD);
            PointHolder pointHolderD = prePointHolderMap.get(pointerIdD);
            pointHolderD.x = event.getX(pointerIndexD);
            pointHolderD.y = event.getY(pointerIndexD);
        }


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


    // 取旋转角度
    private float getDegree(PointHolder pre, PointHolder cur) {
        //得到两个手指间的旋转角度
        double delta_x = cur.x - pre.x;
        double delta_y = cur.y - pre.y;
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }


/**
 * 所有代码里面必须判断 map里面的值是否为null
 */
}
