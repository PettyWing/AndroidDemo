package com.komect.baiduttsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void speak(View view) {
        MyApplication.getInstance().getBaiduTTS().speak("测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用测试用");
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
