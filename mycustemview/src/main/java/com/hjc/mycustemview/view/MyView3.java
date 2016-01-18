package com.hjc.mycustemview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.mycustemview.R;

/**
 * Created by Han on 2016/1/18.
 * email:hanjianchun_happy@163.com
 */
public class MyView3 extends View {

//    <attr name="firstLength" format="integer"/>
//    <attr name="secondLength" format="integer"/>
//    <attr name="speed" format="integer"/>
//    <attr name="customColor" format="color"/>
    /**
     * 第一个勾的长度
     */
    private int firstLength;
    /**
     * 第二个勾的长度
     */
    private int secondLength;
    /**
     * 自定义勾的颜色
     */
    private int customColor;

    /**
     * 定义画笔
     */
    private Paint mPaint;
    /**
     * 临时的长度，用于++
     */
    private int tempFirst=0,tempSecond = 0;

    public MyView3(Context context) {
        this(context,null);
    }

    public MyView3(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取定义的属性并复制
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomYes,defStyleAttr,0);

        firstLength = a.getInt(R.styleable.CustomYes_firstLength, 35);
        secondLength = a.getInt(R.styleable.CustomYes_secondLength, 80);
        customColor = a.getColor(R.styleable.CustomYes_customColor, Color.BLUE);
        a.recycle();

        mPaint = new Paint();
        /**
         * 启动线程画图
         */
        final Thread drawLines = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if (firstLength==tempFirst){
                        if(tempSecond==secondLength){
                            tempFirst = 0;
                            tempSecond= 0;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else
                            tempSecond+=5;
                    }else
                        tempFirst+=5;
                    postInvalidate();
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        drawLines.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth((float) 5.0);
        mPaint.setColor(customColor);
        canvas.drawLine(50, 50, tempFirst+50, tempFirst+50, mPaint);
        if(tempFirst == firstLength)
            canvas.drawLine(tempFirst+50,tempFirst+50,tempSecond+firstLength+50,firstLength-tempSecond+50,mPaint);
    }

}
