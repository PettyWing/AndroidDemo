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
import com.xyc.accountbook.bean.AccountDetail;
import com.xyc.accountbook.databinding.ActivityAddBinding;
import com.xyc.accountbook.presenter.DbPresenter;
import com.xyc.accountbook.widget.InputBox;

import java.util.ArrayList;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public class AddAccountActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private ActivityAddBinding binding;
    private static final String TAG = "AddAccountActivity";
    private ArrayList<AccountDetail> result = new ArrayList<>();

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
        binding.toolbar.setOnMenuItemClickListener(this);
    }

    /**
     * 添加更多的内容
     *
     * @param view
     */
    public void onAddContentClick(View view) {
        if (TextUtils.isEmpty(((InputBox) (binding.llContainer.getChildAt(binding.llContainer.getChildCount() - 1))).getValue())) {
            Toast.makeText(AddAccountActivity.this, "别急，写完一个再添加吧", Toast.LENGTH_SHORT).show();
            return;
        }
        binding.llContainer.addView(new InputBox(this));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveData();
            Toast.makeText(AddAccountActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
        return true;
    }

    private void saveData() {
        String name = ((InputBox) (binding.llContainer.getChildAt(0))).getValue();
        String account = ((InputBox) (binding.llContainer.getChildAt(1))).getValue();
        String password = ((InputBox) (binding.llContainer.getChildAt(2))).getValue();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(AddAccountActivity.this, "名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(AddAccountActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(AddAccountActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < binding.llContainer.getChildCount(); i++) {
            InputBox inputBox = (InputBox) binding.llContainer.getChildAt(i);
            if (TextUtils.isEmpty(inputBox.getTitle())) {
                Toast.makeText(AddAccountActivity.this, "自定义的名称不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(inputBox.getValue())) {
                Toast.makeText(AddAccountActivity.this, "自定义的内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            result.add(new AccountDetail(inputBox.getTitle(), inputBox.getValue()));
        }
        DbPresenter.save(name, account, password, result);
    }
}
