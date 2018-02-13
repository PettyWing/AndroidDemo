package com.komect.androidsharedpreferencedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SPUtil spUtil;
    private EncryptSPUtil encryptSPUtil;
    private static final String KEY = "key";
    private String word = "j_cXra0pqLRqlUi_ZTsMiA==";
    private String defaultValue = "defaultValue";
    private StringBuilder result;
    private TextView txResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spUtil = new SPUtil(this);
        encryptSPUtil = new EncryptSPUtil(this);
        txResult = (TextView) findViewById(R.id.tx_result);
        result = new StringBuilder();
        initOnclick();
    }

    /**
     * 所有点击事件的处理
     */
    private void initOnclick() {

        // 普通存储
        findViewById(R.id.btn_normal_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 重新初始化Stringbuffer
                result = new StringBuilder();
                result.append("普通存储：")
                        .append(word)
                        .append("\n");
                spUtil.putString(KEY, word);
                updateResult(result.toString());
            }
        });

        // 普通获取
        findViewById(R.id.btn_normal_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.append("普通获取：")
                        .append(spUtil.getString(KEY, defaultValue))
                        .append("\n");
                updateResult(result.toString());
            }
        });

        // 加密存储
        findViewById(R.id.btn_encrypt_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 重新初始化Stringbuffer
                result = new StringBuilder();
                encryptSPUtil.putString(KEY, word);
                result.append("加密存储：")
                        .append(word)
                        .append("\n")
                        .append("加密后：")
                        .append(AESUtil.encode(String.valueOf(word)))
                        .append("\n");
                updateResult(result.toString());
            }
        });

        // 解密获取
        findViewById(R.id.btn_encrypt_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.append("解密获取：")
                        .append(encryptSPUtil.getString(KEY, defaultValue))
                        .append("\n");
                updateResult(result.toString());
            }
        });

    }

    /**
     * 更新结果
     */
    private void updateResult(String result) {
        txResult.setText(result);
    }
}
