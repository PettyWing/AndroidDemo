package com.xyc.accountbook.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xyc.accountbook.R;

/**
 * Created by xieyusheng on 2018/8/20.
 */

public class LockTopView extends View {

    private Paint mPaint;
    private int width;
    private int height;
    private int padding;

    public LockTopView(Context context) {
        super(context);
    }

    public LockTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPaint();

        int widthsize = MeasureSpec.getSize(widthMeasureSpec);      //取出宽度的确切数值
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);      //取出宽度的测量模式
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);    //取出高度的确切数值
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);    //取出高度的测量模式
        if (widthmode == MeasureSpec.EXACTLY) {
            width = widthsize;
        } else {
            width = (int) (getResources().getDimension(R.dimen.lock_top_width_default));
        }
        if (heightmode == MeasureSpec.EXACTLY) {
            height = heightsize;
        } else {
            height = (int) (getResources().getDimension(R.dimen.lock_top_height_default));
        }
        setMeasuredDimension(width, height);
    }

    private void initPaint() {
        // 初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(getResources().getDimension(R.dimen.lock_stroke));

        padding = (int) (getResources().getDimension(R.dimen.lock_stroke)) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTop(canvas);
    }


    private void drawTop(Canvas canvas) {
        Path path = new Path();
        path.moveTo(padding, height);
        path.lineTo(padding, height * 5 / 6);
        path.addArc(padding, height * 5 / 6 - width / 2, width - padding, height * 5 / 6 + width / 2, 180, 180);
        path.lineTo(width - padding, height);
        canvas.drawPath(path, mPaint);
    }
}
