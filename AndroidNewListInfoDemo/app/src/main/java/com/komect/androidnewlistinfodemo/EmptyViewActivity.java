package com.komect.androidnewlistinfodemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.komect.androidnewlistinfodemo.databinding.ActivityEmptyBinding;

/**
 * Created by xieyusheng on 2018/3/6.
 */

public class EmptyViewActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEmptyBinding binding;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_empty);

        // 添加增加的事件
        binding.btAdd.setOnClickListener(this);
        initHeader();
    }

    @Override
    public void onClick(View view) {
        showHeader();
    }

    /**
     * 初始化header
     */
    private void initHeader() {
        // 新建header
        header = LayoutInflater.from(this).inflate(
                R.layout.layout_new_info, null);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvDetail.setText("有数据");
                hideHeader();
            }
        });
        binding.emptyView.addView(header);
        header.setVisibility(View.GONE);
    }

    private void hideHeader() {

        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        header.startAnimation(mHiddenAction);
        header.setVisibility(View.GONE);
    }


    private void showHeader() {
        // 添加header的动画
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        header.startAnimation(mShowAction);
        header.setVisibility(View.VISIBLE);


    }
}
