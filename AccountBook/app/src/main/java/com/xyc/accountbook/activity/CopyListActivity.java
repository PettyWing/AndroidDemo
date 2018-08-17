package com.xyc.accountbook.activity;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.FileAdapter;
import com.xyc.accountbook.bean.FileBean;
import com.xyc.accountbook.databinding.ActivityCopyListBinding;
import com.xyc.accountbook.presenter.FilePresenter;
import com.yanzhenjie.permission.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2018/8/17.
 */

public class CopyListActivity extends BaseActivity implements FileAdapter.OnFileItemClickListener {

    ActivityCopyListBinding binding;
    private ArrayList<FileBean> fileList;
    private FileAdapter fileAdapter;
    FilePresenter filePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_copy_list);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
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
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.inset_recyclerview_divider));
        binding.list.addItemDecoration(itemDecoration);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        fileAdapter = new FileAdapter(LayoutInflater.from(this));
        fileAdapter.setOnFileItemClickListener(this);
        binding.list.setAdapter(fileAdapter);
        updateData();
    }

    private void updateData() {
        filePresenter.checkPermission(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                fileList = filePresenter.getAllFiles();
                fileAdapter.setData(fileList);
            }
        });
    }


    @Override
    public void onItemClick(final FileBean fileBean) {
        showListDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        filePresenter.recoverDb(fileBean.getFileName(), true);
                        break;
                    case 1:
                        filePresenter.recoverDb(fileBean.getFileName(), false);
                        break;
                }
            }
        }, "覆盖导入", "合并导入");
    }
}
