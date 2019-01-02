package com.xyc.smalldemo;

import android.app.Application;

import net.wequick.small.Small;

/**
 * Created by xieyusheng on 2018/12/24.
 */

public class SmallApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Small.preSetUp(this);

        Small.setBaseUri("http://example.com/");// 浏览器跳转url
        Small.setUp(this, null);
    }
}
