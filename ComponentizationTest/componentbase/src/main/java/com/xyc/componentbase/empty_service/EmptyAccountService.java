package com.xyc.componentbase.empty_service;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xyc.componentbase.service.IAccountService;

/**
 * Created by xieyusheng on 2018/12/20.
 */

public class EmptyAccountService implements IAccountService{
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle bundle, String tag) {
        return null;
    }
}
