package com.xyc.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xyc.accountbook.R;
import com.xyc.accountbook.databinding.ActivityChatRecordDetailBinding;
import com.xyc.accountbook.util.FileUtil;

/**
 * Created by xieyusheng on 2019/12/2.
 */

public class ChatRecordDetailActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private ActivityChatRecordDetailBinding binding;
    private String fileName;
    private static final String FILE_NAME = "fileName";

    public static void start(Activity activity, String fileName) {
        Intent intent = new Intent(activity, ChatRecordDetailActivity.class);
        intent.putExtra(FILE_NAME, fileName);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_record_detail);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        fileName = getIntent().getStringExtra(FILE_NAME);
        binding.etDetail.setText(FileUtil.readFile(fileName));
    }

    @Override
    public void initView() {
        binding.toolbar.inflateMenu(R.menu.menu_save);
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
            // 添加文件，并且把元素添加fileAdapter
            FileUtil.writeFile(fileName, binding.etDetail.getText().toString(), false);
            finish();
        }
        return true;
    }
}
