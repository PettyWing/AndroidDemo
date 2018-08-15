package com.xyc.accountbook.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xyc.accountbook.R;
import com.xyc.accountbook.databinding.ActivityPswSettingBinding;
import com.xyc.accountbook.presenter.PasswordPresenter;

/**
 * Created by xieyusheng on 2018/8/15.
 */

public class SettingPswActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    ActivityPswSettingBinding binding;
    PasswordPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_psw_setting);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        presenter = new PasswordPresenter(this);
    }

    @Override
    public void initView() {
        binding.llCurrentPsw.setVisibility(presenter.hasOldPsw() ? View.VISIBLE : View.GONE);
        binding.toolbar.inflateMenu(R.menu.menu_add);
        binding.toolbar.setOnMenuItemClickListener(this);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            if (savePsw()) {
                Toast.makeText(SettingPswActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        return true;
    }

    /**
     * @return
     */
    private boolean savePsw() {
        String oldPsw = binding.etCurrentPsw.getText().toString();
        String newPsw = binding.etNewPsw.getText().toString();
        String newPsw2 = binding.etNewPsw2.getText().toString();
        if (presenter.hasOldPsw() && TextUtils.isEmpty(oldPsw)) {
            Toast.makeText(this, "请输入当前密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(newPsw)) {
            Toast.makeText(this, "请输入新的密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(newPsw2)) {
            Toast.makeText(this, "请输入二次确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!TextUtils.equals(newPsw, newPsw2)) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return presenter.save(oldPsw, newPsw);
    }
}
