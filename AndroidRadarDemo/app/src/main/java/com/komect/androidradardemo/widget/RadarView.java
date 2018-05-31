package com.komect.androidradardemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.komect.androidradardemo.ColorUtil;
import com.komect.androidradardemo.DensityUtils;
import com.komect.androidradardemo.R;

/**
 * Created by xieyusheng on 2018/5/31.
 */

public class RadarView extends View {

    private static final String TAG = "RadarView";

    private Paint mPaint; // 画笔
    private Context context;

    private int r; //半径
    private int centerX; // 中心点x坐标
    private int centerY; // 中心点y坐标

    //  传入的参数
    private int circleColor; // 圆的背景色
    private int polygonColor; // 多边形的背景色
    private int polygonCnt; // 多边形的边数
    private int polygonHierarchy; // 多边形的层级

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        r = Math.min(width, height) / 2; // 半径等于宽高取小除以2
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        draPolygon(canvas);
    }

    /**
     * 初始化attrs
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        circleColor = typedArray.getColor(R.styleable.RadarView_circleColor, Color.GRAY);
        polygonColor = typedArray.getColor(R.styleable.RadarView_polygonColor, Color.GRAY);
        polygonCnt = typedArray.getInt(R.styleable.RadarView_polygonCnt, 3);
        polygonHierarchy = typedArray.getInt(R.styleable.RadarView_polygonHierarchy, 5);
    }

    /**
     * 画圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        initPaint(circleColor);
        canvas.drawCircle(centerX, centerY, r, mPaint);
    }

    /**
     * 绘制多边形
     *
     * @param canvas
     */
    private void draPolygon(Canvas canvas) {
        canvas.translate(centerX, centerY); // 移动到以中心点为原点
        Path path = new Path();

        for (int i = polygonHierarchy; i > 1; i--) {
            // 设置每个层级不同的透明度，采用描边模式
            initPaint(Color.parseColor(ColorUtil.addTrans((polygonHierarchy - i) * 100 / polygonHierarchy, 0xffc30e)), r / polygonHierarchy);
//            initPaint(Color.parseColor("#ACACAC"), Paint.Style.STROKE, r / polygonHierarchy);
            int r1 = r * i / polygonHierarchy;
            path.reset();
            for (int j = 0; j < polygonCnt; j++) {
                float x = (float) (r1 * Math.sin(2 * Math.PI * j / polygonCnt));
                float y = (float) (r1 * Math.cos(2 * Math.PI * j / polygonCnt));
                if (j == 0) {
                    // 移动到第一个坐标点
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
                Log.d(TAG, "draPolygon: x=" + x + "\ny=" + y);
            }
            Log.d(TAG, "draPolygon: r=" + r1 + "\npainWidth=" + r / polygonHierarchy);
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }


    /**
     * 初始化画笔 -- 默认设置画笔模式为填充，宽度为2dp
     */
    private void initPaint(int paintColor) {
        initPaint(paintColor, Paint.Style.FILL, 2);
    }

    /**
     * 初始化画笔 -- 默认设置画笔模式为填充
     */
    private void initPaint(int paintColor, int paintWidth) {
        initPaint(paintColor, Paint.Style.FILL, paintWidth);
    }

    /**
     * 初始化画笔 -- 默认设置画笔宽度为2dp
     */
    private void initPaint(int paintColor, Paint.Style style) {
        initPaint(paintColor, style, DensityUtils.dp2px(context, 2));
    }

    /**
     * 初始化画笔
     */
    private void initPaint(int paintColor, Paint.Style style, int paintWidth) {
        mPaint = new Paint();
        mPaint.setColor(paintColor); // 设置画笔颜色
        mPaint.setStyle(style); // 设置画笔风格
        mPaint.setStrokeWidth(paintWidth); // 设置画笔的宽度，单位为px
    }
}
