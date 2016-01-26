package com.hjc.mycustemview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义View5
 * Created by Han on 2016/1/26.
 * email:hanjianchun_happy@163.com
 */
public class MyView5 extends View {

    /**
     * 定义画笔
     */
    private Paint mPaint;

    public MyView5(Context context) {
        this(context, null);
    }

    public MyView5(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
