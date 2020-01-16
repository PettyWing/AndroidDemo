package com.xyc.accountbook.presenter;

import android.content.Context;

import com.xyc.accountbook.bean.FileBean;
import com.xyc.accountbook.util.FileUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by xieyusheng on 2019/12/2.
 */

public class RecordFilePresenter extends BaseFilePresenter {

    private static final String TAG = "RecordFilePresenter";
    public static final String FILE_RECORD = "/ChatRecord";

    public RecordFilePresenter(Context context) {
        super(context);
    }

    @Override
    public String getPath() {
        return FILE_RECORD;
    }

    @Override
    public ArrayList<FileBean> getAllFiles() {
        File f = new File(filePath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }

        File[] files = f.listFiles();

        ArrayList<FileBean> fileList = new ArrayList<>();
        for (File _file : files) {//遍历目录
            if (_file.isFile()) {
                String filePath = _file.getParentFile().toString();//获取文件路径
                String fileName = _file.getName();//获取文件名
                fileList.add(new FileBean(fileName, filePath));
            }
        }
        return fileList;
    }

    @Override
    public FileBean addFile(String fileName) {
        FileUtil.createFile(filePath + "/" + fileName);
        return new FileBean(fileName, filePath);
    }

}
