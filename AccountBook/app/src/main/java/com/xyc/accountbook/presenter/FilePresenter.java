package com.xyc.accountbook.presenter;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xyc.accountbook.bean.AccountInfo;
import com.xyc.accountbook.bean.FileBean;
import com.xyc.accountbook.bean.UserState;
import com.xyc.accountbook.util.FileUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xieyusheng on 2018/8/16.
 */

public class FilePresenter {

    private static final String TAG = "FilePresenter";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private String filePath;
    private String fileName;
    private Context context;

    public FilePresenter(Context context) {
        this.context = context;
        filePath = Environment.getExternalStorageDirectory().getPath() + "/AccountBackup";
    }


    /**
     * @param fileName
     * @param needCover 是否需要覆盖  true 覆盖导入，false 合并导入
     */
    public void recoverDb(final String fileName, final boolean needCover) {
        checkPermission(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                FileInputStream inputStream;
                StringBuilder sb = new StringBuilder("");
                try {
                    inputStream = new FileInputStream(filePath + "/" + fileName);
                    byte temp[] = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(temp)) > 0) {
                        sb.append(new String(temp, 0, len));
                    }
                    Log.d("msg", "readSaveFile: \n" + sb.toString());
                    inputStream.close();
                } catch (Exception e) {
                    Toast.makeText(context, "备份文件读取失败 " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                DbPresenter.saveAll((List<AccountInfo>) new Gson().fromJson(sb.toString(), new TypeToken<List<AccountInfo>>() {
                }.getType()), needCover);
                UserState.newInstance(context).setNeedReloadList(true);
                Toast.makeText(context, "恢复备份文件成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 复制文件到指定路径
     */
    public void copyDb() {
        checkPermission(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                try {
                    int bytesum = 0;
                    int byteread = 0;
                    //context.getExternalFilesDir("")获取外部当前应用文件夹
                    //context.getFilesDir()获取内部当前应用文件夹
                    InputStream inStream = new FileInputStream(context.getExternalFilesDir("") + "/databases/accounts.db");
                    fileName = simpleDateFormat.format(new Date(System.currentTimeMillis())) + "_备份.db";
                    FileUtil.createFile(filePath + "/" + fileName);
                    FileOutputStream fs = new FileOutputStream(filePath + "/" + fileName);
                    byte[] buffer = new byte[1444];
                    int length;
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread; //字节数 文件大小
                        System.out.println(bytesum);
                        fs.write(buffer, 0, byteread);
                    }
                    inStream.close();
                    Toast.makeText(context, "复制文件到 " + filePath + "/" + fileName, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "copyDb: " + filePath + "/" + fileName);
                } catch (Exception e) {
                    Toast.makeText(context, "复制文件错误 " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 复制文件到指定路径
     */
    public void copyDbAsJson() {
        checkPermission(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                String msg = DbPresenter.findAll2String();
                FileOutputStream outputStream;

                try {
                    fileName = simpleDateFormat.format(new Date(System.currentTimeMillis())) + "_备份.db";
                    FileUtil.createFile(filePath + "/" + fileName);
                    outputStream = new FileOutputStream(filePath + "/" + fileName);
                    outputStream.write(msg.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    Toast.makeText(context, "复制文件到 " + filePath + "/" + fileName, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "copyDb: " + filePath + "/" + fileName);
                } catch (Exception e) {
                    Toast.makeText(context, "复制文件错误 " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public ArrayList<FileBean> getAllFiles() {
        File f = new File(filePath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }

        File[] files = f.listFiles();

        ArrayList<FileBean> fileList = new ArrayList<>();
        for (File _file : files) {//遍历目录
            if (_file.isFile() && _file.getName().endsWith(".db")) {
                String filePath = _file.getParentFile().toString();//获取文件路径
                String fileName = _file.getName();//获取文件名
                fileList.add(new FileBean(fileName, filePath));
            }
        }
        return fileList;
    }


    public void checkPermission(Action action) {
        AndPermission.with(context)
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(action)
                .start();
    }
}
