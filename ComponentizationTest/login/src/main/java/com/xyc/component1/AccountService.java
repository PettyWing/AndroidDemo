package com.xyc.component1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xyc.componentbase.service.IAccountService;

/**
 * Created by xieyusheng on 2018/12/20.
 */

public class AccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public String getAccountId() {
        return "cmcc1234";
    }

    @Override
    public Fragment newUserFragment(Activity activity, int containerId, FragmentManager manager, Bundle bundle, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        // 创建 UserFragment 实例，并添加到 Activity 中
        Fragment userFragment = new UserFragment();
        transaction.add(containerId, userFragment, tag);
        transaction.commit();
        return userFragment;
    }
}
