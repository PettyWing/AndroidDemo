package com.komect.androidnotificationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity {

    private NotificationUtil notificationUtil;
    private int messageId = 0; // 消息id，每次点击后+1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationUtil = new NotificationUtil(this, messageId);
        initOnclick();
    }

    /**
     * 所有点击事件的处理
     */
    private void initOnclick() {

        // 简单的通知栏
        findViewById(R.id.btn_notify1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationUtil.show("content", "ticker", "title", "contentInfo");
                messageId++;
            }
        });

        // 相同消息id的通知栏，只会更新原通知
        findViewById(R.id.btn_notify2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NotificationUtil(MainActivity.this)
                        .show("content", "ticker", "title", "contentInfo");
                messageId++;
            }
        });
    }
}
