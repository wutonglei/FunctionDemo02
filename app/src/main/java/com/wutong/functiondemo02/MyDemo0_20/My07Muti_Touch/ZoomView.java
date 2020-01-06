package com.wutong.functiondemo02.MyDemo0_20.My07Muti_Touch;

/**
 * Created by jiuman on 2019/8/27.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class ZoomView extends RelativeLayout {
    private MoveListener listener;
    //用于保证手指离开 View不会瞬间移动
    private  boolean isPointerUp=false;
    // 属性变量
    private float translationX; // 移动X
    private float translationY; // 移动Y
    private float scale = 1; // 伸缩比例
    private float rotation; // 旋转角度

    // 移动过程中临时变量
    private float actionX;
    private float actionY;
    private float spacing;
    private float degree;
    private int moveType; // 0=未选择，1=拖动，2=缩放
    private GestureDetector mGestureDetector  ;
    public void setListener(MoveListener listener){
        this.listener=listener;
    }

    public ZoomView(Context context) {
        this(context, null);
    }

    public ZoomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        mGestureDetector =new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mGestureDetector.onTouchEvent(event)){
            if (listener!=null){
                listener.click();
            }
            return true;
        }

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                moveType = 1;
                actionX = event.getRawX();
                actionY = event.getRawY();
                translationX=getTranslationX();
                translationY=getTranslationY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
//                当前不需要旋转和缩放功能所以取消掉
                moveType = 2;
                spacing = getSpacing(event);
                degree = getDegree(event);
                break;
            case MotionEvent.ACTION_MOVE:

                if (listener!=null){
                    listener.move();
                }
                if (moveType == 1) {
                    Log.i(TAG, "xxxxACTION_MOVE: actionX:"+actionX+"actionY:"+actionY);
                    Log.i(TAG, "xxxxACTION_MOVE: getRawX:"+event.getRawX()+"getRawY:"+event.getRawY());
                    if(!isPointerUp){
                    setX(getX()+event.getRawX() - actionX);
                    setY(getY()+event.getRawY() - actionY);
                    }
//                    translationX = translationX + event.getRawX() - actionX;
//                    translationY = translationY + event.getRawY() - actionY;
//                    setTranslationX(translationX);
//                    setTranslationY(translationY);

                        actionX = event.getRawX();
                        actionY = event.getRawY();

                } else if (moveType == 2) {
                    scale = scale * getSpacing(event) / spacing;
                    setScaleX(scale);
                    setScaleY(scale);
                    rotation = rotation + getDegree(event) - degree;
                    if (rotation > 360) {
                        rotation = rotation - 360;
                    }
                    if (rotation < -360) {
                        rotation = rotation + 360;
                    }
                    setRotation(rotation);

                }
                isPointerUp=false;
                break;
            case MotionEvent.ACTION_UP:
                if (listener!=null){
                    float x = getX();
                    float y = getY();
                    Log.i("trh", "onTouchEvent: x:" + event.getX()+" y:"+event.getY());
                    Log.i("trh", "onTouchEvent: getRawX:" + event.getRawX()+" getRawY:"+event.getRawY());
                    listener.up(event.getRawX(),event.getRawY());
                    float x1 = getX();
                    float y1 = getY();
                    Log.i("sda", "onTouchEvent: rsndm");
                }
                moveType = 0;
                Log.i(TAG, "ACTION_UP: moveType:"+moveType);
                actionX = event.getRawX();
                break;

            case MotionEvent.ACTION_POINTER_UP:
//                此时 getRawX还是第一个按下的手指 而不是松开后的
                moveType = 1;

                Log.i(TAG, "ACTION_POINTER_UP: moveType:"+moveType);
                isPointerUp=true;
                Log.i(TAG, "xxxxACTION_POINTER_UP: actionX:"+actionX+"actionY:"+actionY);
                Log.i(TAG, "xxxxACTION_POINTER_UP: ----------------------------------------------------------");
                break;
        }
//        return super.onTouchEvent(event);
        return true;
    }
String TAG="trh"+this.getClass().getSimpleName();
    // 触碰两点间距离
    private float getSpacing(MotionEvent event) {
        //通过三角函数得到两点间的距离
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    // 取旋转角度
    private float getDegree(MotionEvent event) {
        //得到两个手指间的旋转角度
        double delta_x = event.getX(0) - event.getX(1);
        double delta_y = event.getY(0) - event.getY(1);
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }


    public interface  MoveListener{
        void move();
        void up(float x, float y);
        void click();
    }
}