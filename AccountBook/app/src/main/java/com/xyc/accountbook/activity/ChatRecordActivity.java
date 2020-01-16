package com.xyc.accountbook.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xyc.accountbook.R;
import com.xyc.accountbook.adapter.FileAdapter;
import com.xyc.accountbook.bean.FileBean;
import com.xyc.accountbook.databinding.ActivityChatRecordBinding;
import com.xyc.accountbook.presenter.RecordFilePresenter;
import com.xyc.accountbook.util.FileUtil;
import com.yanzhenjie.permission.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2019/12/2.
 */

public class ChatRecordActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, FileAdapter.OnFileItemClickListener {

    private ActivityChatRecordBinding binding;
    private FileAdapter fileAdapter;
    private RecordFilePresenter recordFilePresenter;
    private ArrayList<FileBean> fileList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_record);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        recordFilePresenter = new RecordFilePresenter(this);
    }

    @Override
    public void initView() {
        binding.toolbar.inflateMenu(R.menu.menu_add);
        binding.toolbar.setOnMenuItemClickListener(this);
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
        recordFilePresenter.checkPermission(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                fileList = recordFilePresenter.getAllFiles();
                fileAdapter.setData(fileList);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            showEditDialog(new OnEditConfirmCallback() {
                @Override
                public void getText(String text) {
                    // 添加文件，并且把元素添加fileAdapter
                    fileAdapter.addData(recordFilePresenter.addFile(text));
                }
            });
        }
        return true;
    }

    @Override
    public void onItemClick(FileBean fileBean) {
        ChatRecordDetailActivity.start(this, fileBean.getFile());
    }

    @Override
    public void onItemLongClicked(final FileBean fileBean) {
        showListDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData data = cm.getPrimaryClip();
                        ClipData.Item item = data.getItemAt(0);
                        FileUtil.writeFile(fileBean.getFile(), "\n" + item.getText().toString(), true);
                        Toast.makeText(ChatRecordActivity.this, "添加内容成功", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, "添加剪切板到记录");
    }


}
