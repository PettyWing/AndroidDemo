package com.xyc.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.AccountDetailAdapter;
import com.xyc.accountbook.bean.AccountDetail;
import com.xyc.accountbook.bean.AccountInfo;
import com.xyc.accountbook.bean.UserState;
import com.xyc.accountbook.databinding.ActivityAddBinding;
import com.xyc.accountbook.presenter.DbPresenter;
import com.xyc.accountbook.util.KeyboardUtils;

import java.util.ArrayList;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public class AccountDetailActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private ActivityAddBinding binding;
    private static final String TAG = "AccountDetailActivity";
    private ArrayList<AccountDetail> accountInfos = new ArrayList<>();
    private AccountDetailAdapter accountAdapter;
    private AccountInfo accountInfo;
    public static final String ACCOUNT_INFO = "accountInfo";
    private boolean isNew = false; // 是否是新创建的记录

    public static void create(Activity activity) {
        Intent intent = new Intent(activity, AccountDetailActivity.class);
        activity.startActivity(intent);
    }

    public static void update(Activity activity, AccountInfo info) {
        Intent intent = new Intent(activity, AccountDetailActivity.class);
        intent.putExtra(ACCOUNT_INFO, info);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.hideKeyboard(this);
    }

    @Override
    public void initData() {
        accountInfo = (AccountInfo) getIntent().getSerializableExtra(ACCOUNT_INFO);
        if (accountInfo == null) {
            // 设置为新添加的状态
            accountInfos.add(new AccountDetail("名称", "", true));
            accountInfos.add(new AccountDetail("账号", "", true));
            accountInfos.add(new AccountDetail("密码", "", true));
            isNew = true;
        } else {
            accountInfos = DbPresenter.str2List(accountInfo.getValuesStr());
        }
    }

    @Override
    public void initView() {
        enableSwipe();
        binding.toolbar.inflateMenu(R.menu.menu_save);
        binding.toolbar.setOnMenuItemClickListener(this);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.llContainer.setLayoutManager(new LinearLayoutManager(this));
        accountAdapter = new AccountDetailAdapter(LayoutInflater.from(this));
        accountAdapter.setData(accountInfos);
        binding.llContainer.setAdapter(accountAdapter);
    }

    /**
     * 添加更多的内容
     *
     * @param view
     */
    public void onAddContentClick(View view) {
        if (TextUtils.isEmpty(accountAdapter.getLastData().getValue())) {
            Toast.makeText(AccountDetailActivity.this, "别急，写完一个再添加吧", Toast.LENGTH_SHORT).show();
            return;
        }
        accountAdapter.addData(new AccountDetail("", ""));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            if (saveData()) {
                Toast.makeText(AccountDetailActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                UserState.newInstance(this).setNeedReloadList(true);
                finish();
            }
        }
        return true;
    }

    private boolean saveData() {
        String name = accountAdapter.getItem(0).getValue();
        String account = accountAdapter.getItem(1).getValue();
        String password = accountAdapter.getItem(2).getValue();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(AccountDetailActivity.this, "名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(AccountDetailActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(AccountDetailActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(accountAdapter.getLastData().getTitle())) {
            Toast.makeText(AccountDetailActivity.this, "自定义的名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(accountAdapter.getLastData().getValue())) {
            Toast.makeText(AccountDetailActivity.this, "自定义的内容不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isNew) {
            return DbPresenter.save(name, account, password, accountAdapter.getData());
        } else {
            return DbPresenter.save(accountInfo.getId(), name, account, password, accountAdapter.getData());
        }
    }
}
