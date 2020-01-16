package com.xyc.accountbook.presenter;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import com.xyc.accountbook.bean.FileBean;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;

/**
 * Created by xieyusheng on 2019/12/2.
 */

public abstract class BaseFilePresenter {

    protected String filePath;
    protected String fileName;
    protected Context context;

    public BaseFilePresenter(Context context) {
        this.context = context;
        filePath = Environment.getExternalStorageDirectory().getPath() + getPath();
    }

    /**
     * 获取文件的存储路径
     *
     * @return
     */
    public abstract String getPath();

    /**
     * 获取当前路径下所有文件
     *
     * @return
     */
    public abstract ArrayList<FileBean> getAllFiles();

    public abstract FileBean addFile(String fileName);

    public void checkPermission(Action action) {
        AndPermission.with(context)
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(action)
                .start();
    }
}
