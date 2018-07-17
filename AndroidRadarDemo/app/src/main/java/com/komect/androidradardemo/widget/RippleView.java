package com.komect.androidradardemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xieyusheng on 2018/7/4.
 */

public class RippleView extends View {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private float r = 50;

    public RippleView(Context context) {
        super(context);
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        initPaint();
        canvas.drawCircle(0, 0, r, mPaint);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#8040BCFF")); // 设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL); // 设置画笔风格
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1); // 设置画笔的宽度，单位为px
    }
}
