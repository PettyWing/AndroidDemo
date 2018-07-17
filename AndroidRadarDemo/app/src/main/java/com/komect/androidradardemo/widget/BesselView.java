package com.komect.androidradardemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xieyusheng on 2018/6/7.
 */

public class BesselView extends View {

    private int centerX;
    private int centerY;
    private Paint mPaint = new Paint();
    private PointF start = new PointF(0, 0);
    private PointF end = new PointF(0, 0);
    private PointF control = new PointF(0, 0);

    public BesselView(Context context) {
        super(context);
    }

    public BesselView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        start.x = centerX - 200;
        start.y = centerY;

        end.x = centerX + 200;
        end.y = centerY;

        control.x = centerX;
        control.y = centerY - 100;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawPoint(canvas);
//        drawLine(canvas);
//        drawBessle(canvas);

        drawRect(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        postInvalidate();
        return true;
    }

    private void drawPoint(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);
    }

    private void drawLine(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);
    }

    private void drawBessle(Canvas canvas) {
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLUE);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);  // 用的是控制点和结束点！！！
        canvas.drawPath(path, mPaint);
    }

    private void drawRect(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充

        canvas.translate(centerX, centerY);          // 移动画布(坐标系)

        RectF rectF = new RectF(-200, -200, 200, 200);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();

        path1.addArc(rectF, 90, 180);
        path2.addCircle(0, -100, 100, Path.Direction.CW);
        path3.addCircle(0, 100, 100, Path.Direction.CCW);

        path1.op(path2, Path.Op.UNION);
        path1.op(path3, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);
    }
}
