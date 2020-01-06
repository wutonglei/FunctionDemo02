package com.wutong.functiondemo02.MyDemo0_20.My11Doodle;

import android.graphics.Paint;

/**
 * 每种画笔都继承这个类 并且都是单例
 * Created by jiuman on 2019/12/31.
 */

public abstract class DoodlePaint {

    Paint paint;

    protected DoodlePaint(Paint paint) {
        this.paint = paint;
    }


    public void setPaint(DrawDate drawDate) {
        if (paint == null)
            paint = new Paint();

        paint.setStrokeWidth(drawDate.strokeWidth);
        paint.setColor(drawDate.color);
    }

    public Paint getPaint() {
        return paint;
    }
}
