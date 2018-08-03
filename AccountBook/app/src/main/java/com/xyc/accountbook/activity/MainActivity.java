package com.xyc.accountbook.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.AccountAdapter;
import com.xyc.accountbook.bean.AccountInfo;
import com.xyc.accountbook.databinding.ActivityAccountListBinding;
import com.xyc.accountbook.databinding.DialogLetterHintBinding;
import com.xyc.accountbook.widget.FloatingBarItemDecoration;
import com.xyc.accountbook.widget.IndexBar;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends BaseActivity implements AccountAdapter.OnAccountClickListener {

    private static final String TAG = "MainActivity";
    private ActivityAccountListBinding binding;
    private DialogLetterHintBinding bindingDialog;
    private LinearLayoutManager mLayoutManager;
    private AccountAdapter accountAdapter;
    private ArrayList<AccountInfo> accountInfos;
    private PopupWindow mOperationInfoDialog;
    private LinkedHashMap<Integer, String> mHeaderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_list);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        accountInfos = new ArrayList<>();
        accountInfos.add(new AccountInfo("卫星"));
        for (int i = 0; i < 10; i++) {
            AccountInfo accountInfo = new AccountInfo("谢谢");
            accountInfos.add(accountInfo);
        }
        preOperation();
    }

    @Override
    public void initView() {
        binding.accountList.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        binding.accountList.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.accountList.addItemDecoration(
                new FloatingBarItemDecoration(this, mHeaderList));
        accountAdapter = new AccountAdapter(LayoutInflater.from(this), accountInfos);
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
    }

    @Override
    public void onItemClicked(AccountInfo bean) {
        Log.d(TAG, "onItemClicked: ");
    }


    /**
     * 进入添加账号的页面
     *
     * @param view
     */
    public void onAddAccountClick(View view) {
        startActivity(new Intent(this, AddAccountActivity.class));
    }

    /**
     * calculate the TAG and add to {@link #mHeaderList}
     */
    private void preOperation() {
        mHeaderList = new LinkedHashMap<>();
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
