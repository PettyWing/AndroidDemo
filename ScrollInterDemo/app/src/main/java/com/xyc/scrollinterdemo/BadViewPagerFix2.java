package com.xyc.scrollinterdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xieyusheng on 2018/9/18.
 */

public class BadViewPagerFix2 extends ViewPager {

    public BadViewPagerFix2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
            super.onInterceptTouchEvent(ev);
            return false;
        } else {
            return true;
        }
    }
}
