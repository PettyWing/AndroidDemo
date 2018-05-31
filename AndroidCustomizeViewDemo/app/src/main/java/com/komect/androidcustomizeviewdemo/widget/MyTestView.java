package com.komect.androidcustomizeviewdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.komect.androidcustomizeviewdemo.R;

/**
 * Created by xieyusheng on 2018/5/21.
 */

public class MyTestView extends View {

    private int defaultSize;

    public MyTestView(Context context) {
        super(context);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 在Android library中不能使用switch-case语句访问资源ID的原因分析及解决方案
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        defaultSize = typedArray.getDimensionPixelSize(R.styleable.MyView_default_size, 100);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getSize(defaultSize, widthMeasureSpec);
        int height = getSize(defaultSize, heightMeasureSpec);
        if (width > height) {
            width = height;
        } else if (width < height) {
            height = width;
        }
        // 重新设置width和height
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        int centerX = getLeft() + r;
        int centerY = getTop() + r;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, r, paint);
    }

    private int getSize(int defaultSize, int measureSpec) {
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                return defaultSize;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                return size;
            default:
                return size;
        }
    }
}
