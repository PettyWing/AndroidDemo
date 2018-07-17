package com.komect.androidradardemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.komect.androidradardemo.DensityUtils;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xieyusheng on 2018/6/5.
 */

public class LoadingView extends View {

    private static final String TAG = "LoadingView";
    private Paint mTxtPaint;
    private Paint mBgPaint;
    private Context context;
    private StringBuffer loadingTxt = new StringBuffer("等待工单");
    private static final int MARGIN_TOP = 5;
    private static final int MARGIN_LEFT = 10;
    private static final int TXT_SIZE = 14;
    private int cnt = 0;
    private int width;
    private int height;
    // 定时任务
    private ScheduledExecutorService pool;

    private boolean isLoading = true;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPool();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initTxtPaint();
        int size[] = getTextWidthAndHeight(mTxtPaint, "等待工单....");
        width = size[0] + DensityUtils.dp2px(context, MARGIN_LEFT * 2);
        height = size[1] + DensityUtils.dp2px(context, MARGIN_TOP * 2);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackGround(canvas);
        if (isLoading) {
            drawLoading(canvas);
        } else {
            drawLoadingStop(canvas);
        }
    }

    private void drawBackGround(Canvas canvas) {
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.BLACK);
        mBgPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, 15, 15, mBgPaint);
    }

    private void drawLoading(Canvas canvas) {
        canvas.drawText(loadingTxt.toString(), width / 2, height / 2 + MARGIN_TOP * 2, mTxtPaint);
    }

    private void drawLoadingStop(Canvas canvas) {
        canvas.drawText("停止抢单", width / 2, height / 2 + MARGIN_TOP * 2, mTxtPaint);
    }

    private void initTxtPaint() {
        // 初始化画笔
        mTxtPaint = new Paint();
        mTxtPaint.setColor(Color.WHITE);
        mTxtPaint.setStyle(Paint.Style.FILL);
        mTxtPaint.setTextSize(DensityUtils.sp2px(context, TXT_SIZE));
        mTxtPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initPool() {
        if (pool != null) {
            return;
        }
        pool = Executors.newScheduledThreadPool(1);
        cnt = 0;
        loadingTxt = new StringBuffer("等待工单");
        pool.scheduleAtFixedRate(mTimerTask, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void stopPool() {
        if (pool == null) {
            return;
        }
        pool.shutdownNow();
        pool = null;
        postInvalidate();
    }

    private int[] getTextWidthAndHeight(Paint paint, String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int with = rect.left + rect.right;
        int height = DensityUtils.sp2px(context, TXT_SIZE);
        int size[] = new int[2];
        size[0] = with;
        size[1] = height;
        return size;
    }

    /**
     * 定时500ms任务
     */
    private final TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (cnt == 4) {
                cnt = 0;
                loadingTxt = new StringBuffer("等待工单");
            } else {
                cnt++;
                loadingTxt.append(".");
            }
            postInvalidate();
        }
    };


    public void startLoading() {
        isLoading = true;
        initPool();
    }

    public void stopLoading() {
        isLoading = false;
        stopPool();
    }
}
