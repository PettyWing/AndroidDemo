package com.xyc.accountbook.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.ADDialogAdapter;
import com.xyc.accountbook.bean.AccountInfo;
import com.xyc.accountbook.databinding.DialogAccountDetailBinding;
import com.xyc.accountbook.presenter.DbPresenter;
import com.xyc.accountbook.util.AppUtils;

/**
 * Created by xieyusheng on 2018/8/10.
 */

public class AccountDetailDialog extends Dialog implements ADDialogAdapter.OnCopyClickListener {

    DialogAccountDetailBinding binding;
    LinearLayoutManager mLayoutManager;
    ADDialogAdapter dialogAdapter;
    private Context context;

    public AccountDetailDialog(@NonNull Context context) {
        super(context, R.style.NormalDialog);    //自定义style主要去掉标题，标题将在setCustomView中自定义设置
        this.context = context;
        initView();
    }

    private void initView() {
        binding = DialogAccountDetailBinding.inflate(getLayoutInflater());
        binding.container.setLayoutManager(mLayoutManager = new LinearLayoutManager(context));
        dialogAdapter = new ADDialogAdapter(LayoutInflater.from(context));
        dialogAdapter.setOnCopyClickListener(this);
        binding.container.setAdapter(dialogAdapter);
        setContentView(binding.getRoot());
    }

    public AccountDetailDialog setData(AccountInfo data) {
        binding.title.setText(data.getName());
        showDetails(data);
        binding.executePendingBindings();
        return this;
    }

    private void showDetails(AccountInfo data) {
        dialogAdapter.setData(DbPresenter.str2List(data.getValuesStr()));
    }

    @Override
    public void onCopyClick(String value) {
        AppUtils.copy(value, context);
        Toast.makeText(context, "已复制：" + value, Toast.LENGTH_SHORT).show();
    }
}
