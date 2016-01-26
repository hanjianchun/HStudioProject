package com.hjc.mycustemview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.mycustemview.R;

/**
 * Created by Han on 2016/1/18.
 * email:hanjianchun_happy@163.com
 */
public class MyView4 extends View {

//    <attr name="firstLineLength" format="integer"/>
//    <attr name="secondLength" format="integer"/>
//    <attr name="lineColor" format="color"/>
//    <attr name="cicleColor" format="color"/>
//    <attr name="cicleExtend" format="integer"/>
//    <attr name="cicleSpeed" format="integer"/>

    private int firstLineLength;
    private int secondLength;
    private int lineColor;
    private int cicleColor;
    private int cicleExtend;
    private int cicleSpeed;


    private Paint mPaint;

    private boolean flag = true;

    private int tempFirst,tempSecond;

    public MyView4(Context context) {
        this(context, null);
    }

    public MyView4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgessYes,defStyleAttr,0);

        firstLineLength = a.getInt(R.styleable.CustomProgessYes_firstLineLength, 35);
        secondLength = a.getInt(R.styleable.CustomProgessYes_secondLineLength, 80);
        lineColor = a.getColor(R.styleable.CustomProgessYes_lineColor, Color.BLUE);
        cicleColor = a.getColor(R.styleable.CustomProgessYes_cicleColor, Color.BLUE);
        cicleExtend = a.getInt(R.styleable.CustomProgessYes_cicleExtend, 30);
        cicleSpeed = a.getInt(R.styleable.CustomProgessYes_cicleSpeed, 60);
        a.recycle();

        mPaint = new Paint();
        cicleSpeed = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    cicleSpeed+=1;
                    if(cicleSpeed>360){
                        flag = false;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }

                while(!flag){

                    if (firstLineLength==tempFirst){
                        if(tempSecond==secondLength){
                            /**
                             * 如果第二根线画完
                             */
                            flag = true;
                        }else
                            tempSecond+=1;
                    }else
                        tempFirst+=1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    postInvalidate();
                }

            }
        }).start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        //圆半径
        int radius = 200;
        //圆宽度
        mPaint.setStrokeWidth(10);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setAntiAlias(true); // 消除锯齿
        if(cicleSpeed<360) {
            RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
            mPaint.setColor(cicleColor);
            canvas.drawArc(oval, -90, cicleSpeed, false, mPaint);
        }else{
            mPaint.setColor(cicleColor);
            canvas.drawCircle(center, center, radius, mPaint);


            /**
             * 计算出偏移量，否则第二跟线会超出边界
             */
            int deviation = secondLength-firstLineLength;
            /**
             * 设置线宽
             */
            mPaint.setStrokeWidth((float) 10.0);
            /**
             * 设置自定义画笔颜色
             */
            mPaint.setColor(lineColor);
            /**
             * 画第一跟线
             */
            canvas.drawLine(250, 300, tempFirst+250, tempFirst+300, mPaint);
            /**
             * 如果第一跟线长度达到设定的长度就开始画第二根线
             */
            if(tempFirst == firstLineLength)
                canvas.drawLine(tempFirst+250,tempFirst+300,tempSecond+tempFirst+250,tempFirst+300-tempSecond,mPaint);

        }
    }
}
