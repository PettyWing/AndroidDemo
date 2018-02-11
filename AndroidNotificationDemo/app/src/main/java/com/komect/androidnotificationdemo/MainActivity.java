package com.komect.androidnotificationdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE = "bundle";
    private static final String CONTENT = "content";
    private static final String TITLE = "title";
    private static final String CONTENT_INFO = "contentInfo";
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

        final String content = "content";
        final String ticker = "ticker";
        final String title = "title";
        final String contentInfo = "contentInfo";


        // 简单的通知栏
        findViewById(R.id.btn_notify1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationUtil.show(content, ticker, title, contentInfo);
                messageId++;
            }
        });

        // 相同消息id的通知栏，只会更新原通知
        findViewById(R.id.btn_notify2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NotificationUtil(MainActivity.this)
                        .show(content, ticker, title, contentInfo);
                messageId++;
            }
        });

        // 跳转详情页面的通知
        findViewById(R.id.btn_notify3).setOnClickListener(new View.OnClickListener() {
            /**
             * @param view
             */
            @Override
            public void onClick(View view) {
                // 通知栏相关数据

                // 创建前往详情页面的intent
                Intent intent = new Intent(MainActivity.this, NotificationDetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(CONTENT, content);
                bundle.putString(TITLE, title);
                bundle.putString(CONTENT_INFO, contentInfo);
                intent.putExtra(BUNDLE, bundle);

                PendingIntent pIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        (int) SystemClock.uptimeMillis(),
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                new NotificationUtil(MainActivity.this)
                        .show(content, ticker, title, contentInfo, pIntent);
                messageId++;
            }
        });
    }
}
