package com.xyc.accountbook.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.xyc.accountbook.R;
import com.xyc.accountbook.bean.UserState;
import com.xyc.accountbook.databinding.ActivitySettingBinding;
import com.xyc.accountbook.presenter.FilePresenter;
import com.xyc.accountbook.presenter.FingerPointManager;

/**
 * Created by xieyusheng on 2018/8/15.
 */

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    ActivitySettingBinding binding;
    FingerPointManager fingerPointManager;
    private FilePresenter filePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        fingerPointManager = new FingerPointManager(this);
        binding.switchFingerPoint.setChecked(UserState.newInstance(this).isFingerPointEnable());
        filePresenter = new FilePresenter(this);
    }

    @Override
    public void initView() {
        enableSwipe();
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.switchFingerPoint.setOnCheckedChangeListener(this);
        binding.btChangePsw.setOnClickListener(this);
        binding.recoverCopy.setOnClickListener(this);
        binding.createCopy.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (!fingerPointManager.isHardwareDected()) {
                Toast.makeText(this, "您的设备不支持指纹识别", Toast.LENGTH_SHORT).show();
                binding.switchFingerPoint.setChecked(false);
            } else if (!fingerPointManager.isFingerOpen()) {
                Toast.makeText(this, "您的设备还没有录入指纹", Toast.LENGTH_SHORT).show();
                binding.switchFingerPoint.setChecked(false);
            } else {
                UserState.newInstance(this).setFingerPointEnable(true);
            }
        } else {
            UserState.newInstance(this).setFingerPointEnable(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_changePsw:
                startActivity(new Intent(this, SettingPswActivity.class));
                break;
            case R.id.create_copy:
                filePresenter.copyDbAsJson();
                break;
            case R.id.recover_copy:
                startActivity(new Intent(this, CopyListActivity.class));
                break;
        }
    }
}
