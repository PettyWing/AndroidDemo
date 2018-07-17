package com.komect.androidradardemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.komect.androidradardemo.bean.PieChartData;

import java.util.List;

/**
 * Created by xieyusheng on 2018/6/1.
 */

public class PieChartView extends View {

    private int r;
    private int mWidth;
    private int mHeight;
    private List<PieChartData> pieChartDataList;
    private Paint mPaint = new Paint();

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        if (null == pieChartDataList)
            return;
        float r = (float) (Math.min(mWidth, mHeight) / 2 );
        float currentAngle = 0;
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(-r, -r, r, r);

        for (PieChartData data : pieChartDataList) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(data.getColor());
            canvas.drawArc(rectF, currentAngle, data.getAngle(), true, mPaint);
            currentAngle += data.getAngle();
        }

    }

    public void setPieChartDataList(List<PieChartData> pieChartDataList) {
        this.pieChartDataList = pieChartDataList;
        initData();
        invalidate();
    }

    private void initData() {
        float value = 0;
        for (PieChartData data : pieChartDataList) {
            value += data.getValue();
        }

        for (PieChartData data : pieChartDataList) {
            data.setPercentage(data.getValue() / value);
            data.setAngle(360 * data.getValue() / value);
        }
    }
}
