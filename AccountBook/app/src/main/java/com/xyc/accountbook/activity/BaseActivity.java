package com.xyc.accountbook.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    public abstract void initData();

    public abstract void initView();

    public void showListDialog(DialogInterface.OnClickListener listener, String... items) {
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(BaseActivity.this);
        listDialog.setItems(items, listener);
        listDialog.show();
    }
}
