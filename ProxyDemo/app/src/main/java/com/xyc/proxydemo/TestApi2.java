package com.xyc.proxydemo;

import android.util.Log;

/**
 * Created by xieyusheng on 2020/1/7.
 */

public class TestApi2 implements Api {
    private static final String TAG = "TestApi2";
    private String word = "test";

    @Override
    public void log() {
        Log.d(TAG, "log: " + TAG);
    }

    @Override
    public void log2() {
        Log.d(TAG, "log2: " + TAG);
    }
}
