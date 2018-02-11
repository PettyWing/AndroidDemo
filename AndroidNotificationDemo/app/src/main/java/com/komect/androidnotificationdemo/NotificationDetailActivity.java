package com.komect.androidnotificationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by xieyusheng on 2018/2/11.
 */

public class NotificationDetailActivity extends AppCompatActivity {

    private static final String BUNDLE = "bundle";
    private static final String CONTENT = "content";
    private static final String TITLE = "title";
    private static final String CONTENT_INFO = "contentInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView txTitle = (TextView) findViewById(R.id.title);
        TextView txContent = (TextView) findViewById(R.id.content);
        TextView txContentInfo = (TextView) findViewById(R.id.contentInfo);

        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra(BUNDLE);
            if (bundle != null) {
                txTitle.setText("title:" + bundle.getString(TITLE));
                txContent.setText("content:" + bundle.getString(CONTENT));
                txContentInfo.setText("contentInfo:" + bundle.getString(CONTENT_INFO));
            }
        }
    }

}
