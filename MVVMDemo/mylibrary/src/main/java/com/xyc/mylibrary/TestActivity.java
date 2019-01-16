package com.xyc.mylibrary;

import android.os.Bundle;

import com.xyc.mylibrary.databinding.ActivityTestBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * Created by xieyusheng on 2019/1/3.
 */

public class TestActivity extends BaseActivity<ActivityTestBinding,TestViewModel>{

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_test;
    }

    @Override
    public int initVariableId() {
        return 0;
    }
}