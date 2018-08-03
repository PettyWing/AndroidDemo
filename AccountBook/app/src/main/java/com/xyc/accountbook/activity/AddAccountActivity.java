package com.xyc.accountbook.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xyc.accountbook.R;
import com.xyc.accountbook.databinding.ActivityAddBinding;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public class AddAccountActivity extends BaseActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        binding.toolbar.inflateMenu(R.menu.menu_add);
    }
}
