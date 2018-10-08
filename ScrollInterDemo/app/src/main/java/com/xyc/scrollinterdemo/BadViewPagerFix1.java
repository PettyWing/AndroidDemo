package com.xyc.scrollinterdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by xieyusheng on 2018/9/18.
 */

public class BadViewPagerFix1 extends ViewPager {

    private static final String TAG = "BadViewPagerFix1";
    int mLastXIntercept;
    int mLastYIntercept;

    public BadViewPagerFix1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                //横坐标位移增量
                int deltaX = x - mLastXIntercept;
                //纵坐标位移增量
                int deltaY = y - mLastYIntercept;
                // TODO: 2018/9/18 进行业务逻辑的判断
                if (Math.abs(deltaX)>Math.abs(deltaY)){
                    // 横向移动则拦截move
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        Log.d(TAG, "onInterceptTouchEvent: "+intercepted);
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }
}
