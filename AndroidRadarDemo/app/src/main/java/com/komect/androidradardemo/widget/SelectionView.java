package com.komect.androidradardemo.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.komect.androidradardemo.R;
import com.komect.androidradardemo.adapter.SelectionAdapter;
import com.komect.androidradardemo.bean.SelectionBean;
import com.komect.androidradardemo.databinding.LayoutSelectionBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2018/7/20.
 */

public class SelectionView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = "SelectionView";
    private LayoutSelectionBinding binding;

    private String[][] selectionData = new String[][]{
            {"时效", "全部", "即将超时", "已超时"},
            {"日期", "全部", "今日", "前一天"}
    };

    private Context context;
    private SelectionAdapter adapter;

    public SelectionView(Context context) {
        this(context, null);
    }

    public SelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_selection, this, false);
        initList();
        binding.btFinish.setOnClickListener(this);
        this.addView(binding.getRoot());
    }

    private void initList() {
        adapter = new SelectionAdapter(context);
        adapter.setData(mockData());
        binding.listContainer.setAdapter(adapter);
    }

    private List<SelectionBean> mockData() {
        List<SelectionBean> list = new ArrayList<>();
        for (int i = 0; i < selectionData.length; i++) {
            SelectionBean bean = new SelectionBean();
            bean.setTitle(selectionData[i][0]);
            List<SelectionBean.Data> datas = new ArrayList<>();
            for (int j = 1; j < selectionData[i].length; j++) {
                SelectionBean.Data data = new SelectionBean.Data();
                data.setId(j - 1);
                data.setTitle(selectionData[i][j]);
                datas.add(data);
            }
            bean.setDatas(datas);
            list.add(bean);
        }
        return list;
    }


    @Override
    public void onClick(View v) {
        for (int i = 0; i < adapter.getResult().size(); i++) {
            Log.d(TAG, "onClick--> " + i + ":" + adapter.getResult().get(i));
        }
    }
}