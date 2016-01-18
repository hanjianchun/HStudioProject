package com.hjc.mycustemview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hjc.mycustemview.R;

import java.util.Random;

/**
 * Created by Han on 2016/1/18.
 * email:hanjianchun_happy@163.com
 */
public class MyView1 extends View {
//    <attr name="myTitleText" format="string"/>
//    <attr name="myTitleColor" format="color"/>
//    <attr name="myTitleSize" format="dimension"/>

    private String myTitleText;

    private int myTitleColor;

    private int myTitleSize;

    private Paint paint;

    private Rect mBounds;

    public MyView1(Context context) {
        this(context, null);
    }

    public MyView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitle,defStyle,0);

        int n = ta.getIndexCount();

        for (int i=0;i<n;i++){
            int attr = ta.getIndex(i);
            switch (attr){
                case R.styleable.CustomTitle_myTitleText:
                    myTitleText = ta.getString(i);
                    break;
                case R.styleable.CustomTitle_myTitleColor:
                    myTitleColor = ta.getColor(i,Color.BLACK);
                    break;
                case R.styleable.CustomTitle_myTitleSize:
                    myTitleSize = ta.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        ta.recycle();

        paint = new Paint();

        mBounds = new Rect();

        paint.getTextBounds(myTitleText,0,myTitleText.length(),mBounds);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myTitleText = randomText();
                postInvalidate();
            }
        });

    }

    /**
     * 获取随机数
     * @return
     */
    public String randomText(){

        Random random = new Random();
        return random.nextInt(9999)+"";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        paint.setColor(myTitleColor);
        paint.setTextSize(myTitleSize);
        canvas.drawText(myTitleText, getWidth() / 2 - mBounds.width() / 2, getHeight() / 2 + mBounds.height() / 2, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         *  EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
         *  AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width,height;

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else{
            paint.setTextSize(myTitleSize);
            paint.getTextBounds(myTitleText,0,myTitleText.length(),mBounds);

            float textWidth = mBounds.width();
            int desired = (int) (getPaddingLeft()+textWidth+getPaddingRight());
            width = desired;
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else{
            paint.setTextSize(myTitleSize);
            paint.getTextBounds(myTitleText,0,myTitleText.length(),mBounds);

            float textHeight = mBounds.height();
            int desired = (int) (getPaddingTop()+textHeight+getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width,height);
    }
}
