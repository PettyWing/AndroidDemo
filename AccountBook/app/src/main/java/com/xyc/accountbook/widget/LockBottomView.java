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

public class LockBottomView extends View {

    private Paint mPaint;
    private Paint mCenterPaint;
    private int width;
    private int height;
    private int padding;

    public LockBottomView(Context context) {
        this(context, null);
    }

    public LockBottomView(Context context, @Nullable AttributeSet attrs) {
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
            width = (int) (getResources().getDimension(R.dimen.lock_bottom_width_default));
        }
        if (heightmode == MeasureSpec.EXACTLY) {
            height = heightsize;
        } else {
            height = (int) (getResources().getDimension(R.dimen.lock_bottom_height_default));
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

        mCenterPaint = new Paint();
        mCenterPaint.setColor(Color.WHITE);
        mCenterPaint.setStyle(Paint.Style.FILL);

        padding = (int) (getResources().getDimension(R.dimen.lock_stroke)) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRectangle(canvas);
        drawCenter(canvas);
    }

    /**
     * 画一个圆角矩形
     *
     * @param canvas
     */
    private void drawRectangle(Canvas canvas) {
        Path path = new Path();

        path.addRoundRect(padding, padding, width - padding, height - padding, 10, 10, Path.Direction.CW);

        canvas.drawPath(path, mPaint);
    }

    /**
     * 画一个中心图案
     *
     * @param canvas
     */
    private void drawCenter(Canvas canvas) {
        canvas.translate(width / 2, height * 3 / 8);

        // 画圆
        Path path = new Path();
        path.addCircle(0, 0, height / 8, Path.Direction.CW);
        canvas.drawPath(path, mCenterPaint);

        // 画三角形
        Path triangle = new Path();
        triangle.moveTo(0, -height / 8);
        triangle.lineTo(-width / 12, 5 * height / 16);
        triangle.lineTo(width / 12, 5 * height / 16);
        triangle.close();

        canvas.drawPath(triangle, mCenterPaint);

    }

}

