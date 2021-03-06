package com.xyc.accountbook;

import android.app.Application;

import com.xyc.accountbook.bean.UserState;

import org.litepal.LitePal;

/**
 * Created by xieyusheng on 2018/8/10.
 */

public class AccountBookApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        UserState.newInstance(this);
    }
}
