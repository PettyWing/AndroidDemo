package com.xyc.accountbook.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public abstract class BaseActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    public abstract void initData();

    public abstract void initView();

    public void enableSwipe() {
        //设置滑动模式
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //设置是否可以滑动
        getSwipeBackLayout().setEnableGesture(true);
    }

    protected void showListDialog(DialogInterface.OnClickListener listener, String... items) {
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(BaseActivity.this);
        listDialog.setItems(items, listener);
        listDialog.show();
    }

    protected interface OnEditConfirmCallback {
        void getText(String text);
    }

    protected void showEditDialog(final OnEditConfirmCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle("请输入文件名");    //设置对话框标题
        final EditText edit = new EditText(BaseActivity.this);
        builder.setView(edit);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.getText(edit.getText().toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
