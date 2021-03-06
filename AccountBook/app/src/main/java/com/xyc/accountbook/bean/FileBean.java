package com.xyc.accountbook.bean;

/**
 * Created by xieyusheng on 2018/8/17.
 */

public class FileBean {
    private String fileName;
    private String filePath;

    public FileBean(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFile() {
        return filePath + "/" + fileName;
    }

}
