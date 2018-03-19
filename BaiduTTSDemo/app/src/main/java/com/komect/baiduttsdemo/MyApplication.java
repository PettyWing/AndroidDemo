package com.komect.baiduttsdemo;

import android.app.Application;

import com.komect.baidutts.BaiduTTS;

/**
 * Created by xieyusheng on 2018/3/16.
 */

public class MyApplication extends Application {

    // ================== 初始化参数设置开始 ==========================
    /**
     * 发布时请替换成自己申请的appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    private String appId = "10250719";

    private String appKey = "bUvzFxRyelDpAVDs7PAUGxjC";

    private String secretKey = "eb664a8bfbaddf0c8acba65a9493e44b";

    private BaiduTTS baiduTTS;

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        baiduTTS = new BaiduTTS(this, appId, appKey, secretKey);
    }

    public BaiduTTS getBaiduTTS() {
        return baiduTTS;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
