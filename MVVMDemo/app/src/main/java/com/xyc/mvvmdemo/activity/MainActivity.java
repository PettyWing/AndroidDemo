package com.xyc.mvvmdemo.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.xyc.mvvmdemo.BR;
import com.xyc.mvvmdemo.R;
import com.xyc.mvvmdemo.databinding.ActivityMainBinding;
import com.xyc.mvvmdemo.viewmodel.MainViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    //ActivityLoginBinding类是databinding框架自定生成的,对activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        //View持有ViewModel的引用，如果没有特殊业务处理，这个方法可以不重写
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }
}