package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

import android.content.Context;
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
 *
 * 结果：缩放已经很接近完美
 * 旋转和缩放的震动问题  于上所示
 * 原因分析 ：双指放着不动造成 get获取的值有较大的变化 估计是  get的算法里面是包含角度和缩放得到的值 最后造成反馈   从而使坐标一直在变动
 * 解决方式 ：  getRaw的方式获取值就ok了
 * 实际上  getRaw(int index )并不能用 应该是有办法的但是我不会
 * 所以这边我只能说 坐标点也都跟着变好了
 * 然后我想到了 getRaw 可以获取第一个点的值  那么通过这层关系 计算其他点的绝对位置
 * 就算绝对值 还是问题很多 我决定  缩放单独提取出来写
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
 * 所以我这边也只考虑三指放大 （其实没必要）
 * Created by jiuman on 2019/12/9.
 * <p>
 * 思路 每次MotionEvent.ACTION_POINTER_DOWN  重新计算初始 重心
 */

public class My07_06Muti_Zoom extends RelativeLayout {

    float maxScale;

    float scale = 1;
    float rotation;
    float degree = 0;
    float spacing = 0;
    String TAG = "trh" + this.getClass().getSimpleName();

    public My07_06Muti_Zoom(Context context) {
        this(context, null);
    }

    public My07_06Muti_Zoom(Context context, AttributeSet attrs) {
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

    public My07_06Muti_Zoom(Context context, AttributeSet attrs, int defStyleAttr) {
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

    float preX, preY;
    float currentX, currentY;

    /**
     * 但是 move 事件始终为 0x00000002，也就是说，
     * 在 move 时不论你移动哪个手指，使用 getActionIndex() 获取到的始终是数值 0。
     * 区分Move pointId
     *
     * @param event
     * @return
     */
    int amountPoint = 0;

    int survivalPoint = 0;

    //  pointerId
    Map<Integer, PointHolder> prePointHolderMap = new HashMap<>();
    Map<Integer, PointHolder> curPointHolderMap = new HashMap<>();
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
                scale = 1;
                maxScale=Math.min(1.0f*ScreenUtils.getScreenWidth()/getWidth(),1.0f*ScreenUtils.getScreenHeight()/getHeight());
                break;
            case MotionEvent.ACTION_UP:
                prePointHolderMap.remove(event.getPointerId(currentIndex));
                Log.i(TAG, "ACTION_UP: ");
                gravityPoint.x = 0;
                gravityPoint.y = 0;
                break;

            case MotionEvent.ACTION_MOVE:
                float curSpacing = 0;
                int nullCount = 0;
                for (int i = 0; i < event.getPointerCount(); i++) {
                    getRotation();


                    int pointerId = event.getPointerId(i);
                    //用于getX(pointerIndex)
                    int pointerIndex = event.findPointerIndex(pointerId);

                    PointHolder pointHolderD = prePointHolderMap.get(pointerId);
                    pointHolderD.x = event.getX(pointerIndex);
                    pointHolderD.y = event.getY(pointerIndex);
                    getAbsolutePoint(event, pointHolderD);
                    pathHashMap.get(pointerId).lineTo(pointHolderD.x, pointHolderD.y);
                    Log.i(TAG, "all:" + i + "onTouchEvent: " + "getX:" + event.getX() + "getY:" + event.getY() + "event.getRawX():" + event.getRawX() + "event.getRawY():" + event.getRawY());
                    Log.i(TAG, "allAbcccc:" + i + "X:" + pointHolderD.x + "Y:" + pointHolderD.y);
                    if (midpoint == null)
                        continue;
                    PointHolder pointHolder = prePointHolderMap.get(i);
                    if (pointHolder == null){
                        nullCount++;
                        continue;
                    }

                    if (i >= 1 +nullCount&& i < event.getPointerCount() - 1) {
                        getMidpoint(prePointHolderMap.get(i - 1-nullCount), pointHolder);
                        Log.i(TAG, "onTouchEvent: 转角度前");
                    }

                    if (event.getPointerCount() - 1 == i && i > 0) {

                        Log.i(TAG, "onTouchEvent: getRotation()" + getRotation() + "  getDegree(prePointHolderMap.get(i):" + getDegree(pointHolder));
//不懂为什么不行
                        rotation = rotation + getDegree(pointHolder) - degree;
//                        rotation=getRotation()+getDegree();
                        Log.i(TAG, "onTouchEvent: 转角度");


                        setRotation(rotation);




                        Log.i(TAG, "allAbxxxx:" + i + "X:" + pointHolderD.x + "Y:" + pointHolderD.y);
                    }

                    if (event.getPointerCount() > 1) {
                        curSpacing += getSpacing(pointHolder);
                        Log.i(TAG, "onTouchEvent: 变化curSpacing：" + curSpacing + Thread.currentThread().getName());
                    }
                }
                Log.i(TAG, "onTouchEvent: curSpacing:" + curSpacing + "spacing:" + spacing + curSpacing + Thread.currentThread().getName());
                int pointerCount = event.getPointerCount();
                if (event.getPointerCount() > 1 && curSpacing > spacing / 3) {
                    scale += (curSpacing - spacing) / spacing/2;
                    for (int i = 0; i < event.getPointerCount(); i++) {
                        PointHolder pointHolder = prePointHolderMap.get(i);
                        if (pointHolder == null)
                            continue;
                    }
                    if(scale>maxScale)
                        scale=maxScale;
                    setScaleX(scale);
                    setScaleY(scale);

                }
                break;


            case MotionEvent.ACTION_POINTER_DOWN:
                down(event, currentIndex);
                getGravitys(event);
                initDegreeSpacing(event);


                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "第" + (currentIndex + 1) + "个手指抬起,其id为：" + event.getPointerId(currentIndex));
                prePointHolderMap.remove(event.getPointerId(currentIndex));
                getGravitys(event);
                initDegreeSpacing(event);
                break;
        }
//        invalidate();
//        注意这里一定要消费掉 （主要是消费掉Down 不然后续的内容不会被执行） 不然后续没法搞
        return true;
    }

    private void initDegreeSpacing(MotionEvent event) {
        int nullCount = 0;
        spacing = 0;

        for (int i = 0; i < event.getPointerCount(); i++) {
            PointHolder pointHolder = prePointHolderMap.get(i);
            if (pointHolder == null) {
                nullCount++;
                continue;
            }

            if (i > 1 + nullCount && i < event.getPointerCount() - 1) {
                try {
                    getMidpoint(prePointHolderMap.get(i - 1 - nullCount), pointHolder);
                }
              catch (Exception e){
                  Log.e(TAG, "initDegreeSpacing: "+e.getMessage());
                  Log.e(TAG, "initDegreeSpacing: i - 1 - nullCount"+(i - 1 - nullCount));
                  continue;
              }
                Log.i(TAG, "onTouchEvent: 转角度前");
            }
            nullCount = 0;
            if (event.getPointerCount() - 1 == i && i > 0)
                degree = getDegree(pointHolder);

            spacing += getSpacing(pointHolder);
        }
    }

    private void down(MotionEvent event, int currentIndex) {
        int pointerId = event.getPointerId(currentIndex);
        int pointerIndex = event.findPointerIndex(pointerId);

        PointHolder pointHolder = new PointHolder(event.getX(pointerIndex), event.getY(pointerIndex));
        getAbsolutePoint(event, pointHolder);
        prePointHolderMap.put(pointerId, pointHolder);
        Path path = new Path();
        event.getRawY();
        path.moveTo(event.getX(pointerIndex), event.getY(pointerIndex));
        pathHashMap.put(event.getPointerId(currentIndex), path);
        Log.i(TAG, "第" + (currentIndex + 1) + "个手指按下,其id为：" + event.getPointerId(currentIndex));
    }

    PointHolder aTopLeftPoint = new PointHolder(0, 0);

    public void getAbsolutePoint(MotionEvent event, PointHolder pointHolder) {
//        /**
//         * 左上角的绝对值 todo 这里可以优化  少算几次  但是先放着
//         */
        aTopLeftPoint.x = (float) (event.getRawX() - Math.cos(getRotation()) * event.getX());
        aTopLeftPoint.y = (float) (event.getRawY() - Math.cos(getRotation()) * event.getY());
        pointHolder.x = (float) (aTopLeftPoint.x + Math.cos(getRotation()) * pointHolder.x);
        pointHolder.y = (float) (aTopLeftPoint.y + Math.cos(getRotation()) * pointHolder.y);
        Log.i(TAG, "getAbsolutePoint: getRotation" + getRotation() + "Math.cos(getRotation())：" + Math.cos(getRotation()));
        Log.i(TAG, "getAbsolutePoint: " + "event.getRawX():" + event.getRawX() + "event.getRawY():" + event.getRawY());
        Log.i(TAG, "getAbsolutePoint: " + "getX:" + event.getX() + "getY:" + event.getY());
        Log.i(TAG, "getAbsolutePoint: X:" + pointHolder.x + "Y:" + pointHolder.y);
        Log.i(TAG, "getAbsolutePoint: topX:" + aTopLeftPoint.x + "topY:" + aTopLeftPoint.y);

    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        for (Map.Entry<Integer, Path> entry : pathHashMap.entrySet()) {
//            Integer mapKey = entry.getKey();
//            Path path = entry.getValue();
//            canvas.drawPath(path, mDefaultPaint);
//
//        }
//
//    }

    class PointHolder {
        //只有当Up状态才能把数据初始化 不然就是原来的手指还在继续中
        public float x;
        public float y;

        public PointHolder(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    private PointHolder midpoint = new PointHolder(0, 0);

    //    这里我没有简化   我认为数学上可以简化
    private PointHolder getMidpoint(PointHolder a, PointHolder b) {
        midpoint.x = (a.x + b.x) / 2;
        midpoint.y = (a.y + b.y) / 2;


        return midpoint;
    }

    // 取旋转角度
    private float getDegree(PointHolder a) {
        //得到两个手指间的旋转角度
        double delta_x = midpoint.x - a.x;
        double delta_y = midpoint.y - a.y;
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private PointHolder gravityPoint = new PointHolder(0, 0);


    private void getGravitys(MotionEvent event) {
        float x = 0, y = 0;
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            PointHolder pointHolder = prePointHolderMap.get(i);
            if (pointHolder == null)
                continue;
            x += pointHolder.x;
            y += pointHolder.y;
        }
        x = x / pointerCount;
        y = y / pointerCount;
        gravityPoint.x = x;
        gravityPoint.y = y;
    }


    private float getSpacing(PointHolder a) {
        //通过三角函数得到两点间的距离
        float x = gravityPoint.x - a.x;
        float y = gravityPoint.y - a.y;
        Log.i(TAG, "getSpacing: x:" + x + "y:" + y);
        return (float) Math.sqrt(x * x + y * y);
    }

/**
 * 所有代码里面必须判断 map里面的值是否为null
 */
}
