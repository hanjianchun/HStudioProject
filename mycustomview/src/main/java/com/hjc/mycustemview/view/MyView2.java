package com.hjc.mycustemview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hjc.mycustemview.R;

/**
 * Created by Han on 2016/1/18.
 * email:hanjianchun_happy@163.com
 */
public class MyView2 extends View {

    /*    <attr name="firstColor" format="color"/>
        <attr name="secondColor" format="color"/>
        <attr name="circleWidth" format="dimension"/>
        <attr name="speed" format="integer"/>*/
    private int firstColor;
    private int secondColor;
    private int circleWidth;
    private int speed;

    private Paint mPaint;
    private int mProgress;
    private boolean flag = true;


    public MyView2(Context context) {
        this(context, null);
    }

    public MyView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);

        firstColor = a.getColor(R.styleable.CustomProgressBar_firstColor, Color.BLACK);
        secondColor = a.getColor(R.styleable.CustomProgressBar_secondColor, Color.BLUE);
        circleWidth = a.getDimensionPixelOffset(R.styleable.CustomProgressBar_circleWidth, 25);
        speed = a.getInt(R.styleable.CustomProgressBar_speed, 20);

        a.recycle();

        mPaint = new Paint();

        new Thread() {
            @Override
            public void run() {
                int tempColor = firstColor;
                while (flag) {
                    mProgress++;
                    mPaint.setColor(tempColor);
                    if (mProgress == 360) {
                        mProgress = 0;
                        tempColor = secondColor;
                        secondColor = firstColor;
                        firstColor = tempColor;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(speed);
                    } catch (Exception e) {

                    }
                }
            }
        }.start();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TAG", "点击了");
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int progress = (int) (mProgress / 3.6);
//        if(mProgress>=359) {
//            flag = false;
//            progress = 100;
//            mProgress=360;
//        }

        int center = getWidth() / 2;
        //圆半径
        int radius = center - circleWidth / 2;
        //圆宽度
        mPaint.setStrokeWidth(circleWidth);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setAntiAlias(true); // 消除锯齿

        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        mPaint.setColor(firstColor);
        canvas.drawCircle(center, center, radius, mPaint);
        mPaint.setColor(secondColor);
        canvas.drawArc(oval, -90, mProgress, false, mPaint);

        mPaint = new Paint();
        mPaint.setTextSize(40);
        mPaint.setColor(Color.BLUE);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        canvas.drawText(progress + "%", radius - circleWidth, radius + circleWidth, mPaint);


    }
}
