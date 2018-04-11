package com.komect.baiduttsdemo;

import android.app.Application;

import com.komect.baidutts.BaiduTTS;

/**
 * Created by xieyusheng on 2018/3/16.
 */

public class MyApplication extends Application {



    private BaiduTTS baiduTTS;

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public BaiduTTS getBaiduTTS() {
        return baiduTTS;
    }

    public void setBaiduTTS(BaiduTTS baiduTTS) {
        this.baiduTTS = baiduTTS;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
