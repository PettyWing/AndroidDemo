package com.xyc.proxydemo;

import android.util.Log;

/**
 * Created by xieyusheng on 2020/1/3.
 */

public class TestApi implements Api {
    private static final String TAG = "TestApi";
    private String word = "test";

    public void log() {
        Log.d(TAG, "log: " + TAG);
    }

    @Override
    public void log2() {
        Log.d(TAG, "log2: " + TAG);
    }
}
