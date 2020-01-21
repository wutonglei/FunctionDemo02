package com.wutong.functiondemo02.MyDemo0_20.My14ShowSomePicture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wutong.functiondemo02.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jiuman on 2020/1/21.
 */

public class MyImageView extends View {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    Paint paint;
    Bitmap bitmap;

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    int drawWidth;

    private float mode=3;
    /**
     * 1  2  3种显示方式
     */
    public static final int MODE_1 = 3;
    public static final int MODE_2 = 2;
    public static final int MODE_3 = 1;


    public void setMode(@Mode int mode) {
        this.mode = mode/3.0f ;
        invalidate();
    }

    @IntDef({MODE_1, MODE_2, MODE_3})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitCenter=bitmap.getWidth()/2;
        int halfShowWidth=(int) (bitmap.getWidth() *mode/2);
        Rect src = new Rect(bitCenter-halfShowWidth, 0, bitCenter+halfShowWidth, bitmap.getHeight());

        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, (int) (getWidth()*mode), getHeight());

        // 绘制图片
        canvas.drawBitmap(bitmap, src, dst, null);


    }
}
