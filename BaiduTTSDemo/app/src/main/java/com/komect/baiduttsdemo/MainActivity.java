package com.komect.baiduttsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.komect.baidutts.BaiduTTS;

public class MainActivity extends AppCompatActivity {
    // ================== 初始化参数设置开始 ==========================
    /**
     * 发布时请替换成自己申请的appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    public static final String BAIDU_TTS_APP_ID = "10498356";

    public static final String BAIDU_TTS_APP_KEY = "q7fpYLm7yoyELfNBGN9GYI23gtdYyHey";

    public static final String BAIDU_TTS_SECRET_KEY = "NoyxHAxU70NvRUrbcBtiQLeodKg57Mth";


    private String appId = "10250719";

    private String appKey = "bUvzFxRyelDpAVDs7PAUGxjC";

    private String secretKey = "eb664a8bfbaddf0c8acba65a9493e44b";
    private BaiduTTS baiduTTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication.getInstance().setBaiduTTS(new BaiduTTS(this, appId, appKey, secretKey));
    }

    public void speak(View view) {
        MyApplication.getInstance().getBaiduTTS().speak("测试用测试用测试用测试用");
    }

    public void pause(View view) {
        MyApplication.getInstance().getBaiduTTS().pause();
    }

    public void resume(View view) {
        MyApplication.getInstance().getBaiduTTS().resume();
    }

    public void stop(View view) {
        MyApplication.getInstance().getBaiduTTS().stop();
    }
}
