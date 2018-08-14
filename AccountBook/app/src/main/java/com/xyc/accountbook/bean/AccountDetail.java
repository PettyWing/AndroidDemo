package com.xyc.accountbook.bean;

import java.io.Serializable;

/**
 * Created by xieyusheng on 2018/8/13.
 */

public class AccountDetail implements Serializable{

    private String title;
    private String value;
    private boolean fixed;

    public AccountDetail(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public AccountDetail(String title, String value, boolean fixed) {
        this.title = title;
        this.value = value;
        this.fixed = fixed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
