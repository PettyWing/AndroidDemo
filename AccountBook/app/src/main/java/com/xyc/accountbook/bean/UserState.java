package com.xyc.accountbook.bean;

import android.content.Context;
import android.text.TextUtils;

import com.xyc.accountbook.util.SharedPreferencesUtil;

/**
 * Created by xieyusheng on 2018/8/15.
 */

public class UserState {

    private static UserState instance;
    private SharedPreferencesUtil spUtil;
    public static final String SP_FINGER_POINT = "spFingerPoint";
    public static final String SP_PASSWORD = "spPassword";

    public static UserState newInstance(Context context) {
        if (instance == null) {
            instance = new UserState(context);
        }
        return instance;
    }

    public UserState(Context context) {
        spUtil = new SharedPreferencesUtil(context);
        setFingerPointEnable(spUtil.getBoolean(SP_FINGER_POINT, false));
        setPassword(spUtil.getString(SP_PASSWORD, ""));
    }

    private boolean fingerPointEnable; // 指纹识别是否开启
    private boolean needPassword;  // 是否需要认证
    private boolean needReloadList = true; // 是否需要重新刷新列表页面
    private String password;

    public boolean isFingerPointEnable() {
        return fingerPointEnable;
    }

    public void setFingerPointEnable(boolean fingerPointEnable) {
        this.fingerPointEnable = fingerPointEnable;
        spUtil.putBoolean(SP_FINGER_POINT, fingerPointEnable);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        spUtil.putString(SP_PASSWORD, password);
    }

    public boolean isNeedPassword() {
        return fingerPointEnable || !TextUtils.isEmpty(password);
    }

    public boolean isNeedReloadList() {
        return needReloadList;
    }

    public void setNeedReloadList(boolean needReloadList) {
        this.needReloadList = needReloadList;
    }
}
