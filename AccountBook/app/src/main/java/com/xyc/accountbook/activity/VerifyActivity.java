package com.xyc.accountbook.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.KeyboardAdapter;
import com.xyc.accountbook.bean.UserState;
import com.xyc.accountbook.databinding.ActivityVerifyBinding;
import com.xyc.accountbook.presenter.FingerPointManager;
import com.xyc.accountbook.presenter.PasswordPresenter;
import com.xyc.accountbook.widget.LockView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2018/8/14.
 */

public class VerifyActivity extends BaseActivity implements KeyboardAdapter.OnKeyboardItemClickListener, FingerPointManager.FingerPrintResultListener, LockView.OnLockOpenListener {

    private ActivityVerifyBinding binding;
    private List<String> datas;
    private KeyboardAdapter adapter;
    private StringBuffer currentPassword = new StringBuffer();
    private PasswordPresenter presenter;
    private FingerPointManager fingerPointManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        presenter = new PasswordPresenter(this);
        fingerPointManager = new FingerPointManager(this);
        datas = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if (i < 9) {
                datas.add(String.valueOf(i + 1));
            } else if (i == 9) {
                datas.add("Exit");
            } else if (i == 10) {
                datas.add("0");
            } else {
                datas.add("Del");
            }
        }

    }

    @Override
    public void initView() {
        if (!UserState.newInstance(this).isNeedPassword()) {
            gotoMainActivity();
            return;
        }
        binding.keyboard.setLayoutManager(new GridLayoutManager(this, 3));
        // 设置水平divier
        DividerItemDecoration dividerHz = new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL);
        dividerHz.setDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.verify_divider)));
        binding.keyboard.addItemDecoration(dividerHz);
        // 设置垂直divier
        DividerItemDecoration dividerVer = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL);
        dividerVer.setDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.verify_divider)));
        binding.keyboard.addItemDecoration(dividerVer);

        adapter = new KeyboardAdapter(LayoutInflater.from(this));
        adapter.setData(datas);
        adapter.setOnKeyboardItemClickListener(this);
        binding.keyboard.setAdapter(adapter);

        binding.lockView.setOnLockOpenListener(this);

        if (UserState.newInstance(this).isFingerPointEnable()) {
            // 指纹识别开启后，开始指纹识别
            fingerPointManager.startAuthenticate();
        }
        fingerPointManager.setFingerPrintResultListener(this);
    }

    @Override
    public void onAddClicked(String value) {
        currentPassword.append(value);
        binding.tvPsw.setText(encode(currentPassword.toString()));
        binding.notifyChange();
        verifyPsw(currentPassword.toString());
    }

    @Override
    public void onDeleteClicked() {
        if (currentPassword.length() == 0) {
            return;
        }
        currentPassword.deleteCharAt(currentPassword.length() - 1);
        binding.tvPsw.setText(encode(currentPassword.toString()));
        binding.notifyChange();
    }

    @Override
    public void onBackClicked() {
        finish();
    }

    /**
     * 验证密码
     */
    private void verifyPsw(String psw) {
        if (presenter.verifyPsw(psw)) {
            binding.lockView.open();
        }
    }

    private String encode(String text) {
        StringBuffer encodeText = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            encodeText.append("*");
        }
        return encodeText.toString();
    }

    @Override
    public void onSuccess() {
        binding.lockView.open();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this, "指纹识别失败", Toast.LENGTH_SHORT).show();
    }

    private void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 锁打开
     */
    @Override
    public void onLockOpen() {
        gotoMainActivity();
    }
}
