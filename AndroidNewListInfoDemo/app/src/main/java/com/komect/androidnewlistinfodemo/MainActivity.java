package com.komect.androidnewlistinfodemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.komect.androidnewlistinfodemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    ActivityMainBinding binding;
    ListAdapter listAdapter;
    List<String> dataList;
    private View header,header2; // 列表的头
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        dataList = new ArrayList<>();
        // 添加下拉listener
        binding.swipeGoogleAndLoadmoreRefreshLayout.setOnRefreshListener(this);

        // 添加adapter
        binding.listView.setAdapter(listAdapter = new ListAdapter(this));
        binding.listView.setEmptyView(binding.emptyView);
        // 添加增加的事件
        binding.btAdd.setOnClickListener(this);
        initHeader();
        initData();
    }

    private void initData() {
        listAdapter.addData(1 + "");
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        listAdapter.removeAllData();
        hideHeader();
        // 取消下拉刷新
        binding.swipeGoogleAndLoadmoreRefreshLayout.setRefreshing(false);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        // list添加数据
        dataList.add(i + "");
        i++;
        // 显示header
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
                listAdapter.setData(dataList);
                hideHeader();
                binding.swipeGoogleAndLoadmoreRefreshLayout.setRefreshing(false);
                listAdapter.notifyDataSetChanged();
            }
        });

        // 新建header
        header2 = LayoutInflater.from(this).inflate(
                R.layout.layout_new_info2, null);
        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdapter.setData(dataList);
                hideHeader();
                binding.swipeGoogleAndLoadmoreRefreshLayout.setRefreshing(false);
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    private void hideHeader() {

        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
//        header.startAnimation(mHiddenAction);
        header2.startAnimation(mHiddenAction);
        binding.listView.removeHeaderView(header2);
//        binding.emptyView.removeView(header);
    }


    private void showHeader() {
        // 添加header的动画
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        // 如果列表中有数据
        if (listAdapter.getData().size() > 0) {
            // 如果当前的header已经大于0了，则不再添加header
            if (binding.listView.getHeaderViewsCount() > 0) {
                return;
            }
            header2.startAnimation(mShowAction);
            binding.listView.addHeaderView(header2);
        } else {
            header.startAnimation(mShowAction);
            binding.emptyView.removeView(header);
            binding.emptyView.addView(header);
        }


    }
}
