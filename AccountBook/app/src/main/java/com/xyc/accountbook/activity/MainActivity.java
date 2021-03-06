package com.xyc.accountbook.activity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.AccountAdapter;
import com.xyc.accountbook.bean.AccountInfo;
import com.xyc.accountbook.bean.UserState;
import com.xyc.accountbook.databinding.ActivityAccountListBinding;
import com.xyc.accountbook.databinding.DialogLetterHintBinding;
import com.xyc.accountbook.presenter.DbPresenter;
import com.xyc.accountbook.widget.AccountDetailDialog;
import com.xyc.accountbook.widget.FloatingBarItemDecoration;
import com.xyc.accountbook.widget.IndexBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements AccountAdapter.OnAccountClickListener, Toolbar.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";
    private ActivityAccountListBinding binding;
    private DialogLetterHintBinding bindingDialog;
    private LinearLayoutManager mLayoutManager;
    private AccountAdapter accountAdapter;
    private List<AccountInfo> accountInfos;
    private PopupWindow mOperationInfoDialog;
    private LinkedHashMap<Integer, String> mHeaderList;
    private FloatingBarItemDecoration floatingBarID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_list);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserState.newInstance(this).isNeedReloadList()) {
            updateData(DbPresenter.findAll());
            UserState.newInstance(this).setNeedReloadList(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserState.newInstance(this).setNeedReloadList(true);
    }

    @Override
    public void initData() {
        mHeaderList = new LinkedHashMap<>();
    }

    @Override
    public void initView() {
        binding.accountList.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));

        // 设置垂直divier
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.inset_recyclerview_divider));
        binding.accountList.addItemDecoration(itemDecoration);

        binding.accountList.addItemDecoration(
                floatingBarID = new FloatingBarItemDecoration(this, mHeaderList));
        accountAdapter = new AccountAdapter(LayoutInflater.from(this));
        accountAdapter.setData(accountInfos);
        accountAdapter.setOnAccountClickListener(this);
        binding.accountList.setAdapter(accountAdapter);
        binding.sideBar.setNavigators(new ArrayList<>(mHeaderList.values()));
        binding.sideBar.setOnTouchingLetterChangedListener(new IndexBar.OnTouchingLetterChangeListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                showLetterHintDialog(s);
                for (Integer position : mHeaderList.keySet()) {
                    if (mHeaderList.get(position).equals(s)) {
                        mLayoutManager.scrollToPositionWithOffset(position, 0);
                        return;
                    }
                }
            }

            @Override
            public void onTouchingStart(String s) {
                showLetterHintDialog(s);
            }

            @Override
            public void onTouchingEnd(String s) {
                hideLetterHintDialog();
            }
        });
        binding.toolbar.inflateMenu(R.menu.menu_main);
        binding.toolbar.setOnMenuItemClickListener(this);
        binding.etSearch.addTextChangedListener(textWatcher);
    }

    @Override
    public void onItemClicked(AccountInfo bean) {
        new AccountDetailDialog(this).setData(bean).show();
    }

    @Override
    public void onItemLongClicked(final AccountInfo bean, final int position) {
        showListDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        AccountDetailActivity.update(MainActivity.this, bean);
                        break;
                    case 1:
                        DbPresenter.delete(bean);
                        updateData(DbPresenter.findAll());
                        break;
                    default:
                        break;
                }
            }
        }, "修改账号", "删除该账号");
    }


    /**
     * 菜单点击
     *
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_menu) {
            startActivity(new Intent(this, SettingActivity.class));
        }
        return true;
    }

    /**
     * 进入添加账号的页面
     *
     * @param view
     */
    public void onAddAccountClick(View view) {
        AccountDetailActivity.create(this);
    }

    /**
     * 添加或者删除账号后，更新数据
     */
    private void updateData(List<AccountInfo> data) {
        accountInfos = data;
        preOperation();
        floatingBarID.updateList(mHeaderList);
        binding.accountList.invalidateItemDecorations();
        accountAdapter.setData(accountInfos);
        binding.sideBar.setNavigators(new ArrayList<>(mHeaderList.values()));
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            updateData(DbPresenter.find(s.toString()));
        }
    };

    /**
     * calculate the TAG and add to {@link #mHeaderList}
     */
    private void preOperation() {
        Collections.sort(accountInfos);
        mHeaderList.clear();
        if (accountInfos.size() == 0) {
            return;
        }
        addHeaderToList(0, accountInfos.get(0).getInitial());
        for (int i = 1; i < accountInfos.size(); i++) {
            if (!accountInfos.get(i - 1).getInitial().equalsIgnoreCase(accountInfos.get(i).getInitial())) {
                addHeaderToList(i, accountInfos.get(i).getInitial());
            }
        }
    }

    private void addHeaderToList(int index, String header) {
        mHeaderList.put(index, header);
    }

    /**
     * related to {@link IndexBar#mOnTouchingLetterChangeListener}
     * show dialog in the center of this window
     *
     * @param s
     */
    private void showLetterHintDialog(String s) {
        if (mOperationInfoDialog == null) {
            bindingDialog = DialogLetterHintBinding.inflate(LayoutInflater.from(this));
            mOperationInfoDialog = new PopupWindow(bindingDialog.getRoot(), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, false);
            mOperationInfoDialog.setOutsideTouchable(true);
        }
        bindingDialog.letter.setText(s);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mOperationInfoDialog.showAtLocation(getWindow().getDecorView().findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
            }
        });
    }

    private void hideLetterHintDialog() {
        mOperationInfoDialog.dismiss();
    }

}
