package com.komect.androidradardemo.bean;

import java.util.List;

/**
 * Created by xieyusheng on 2018/7/23.
 */

public class SelectionBean {
    private String title;
    private List<Data> datas;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static class Data {
        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
