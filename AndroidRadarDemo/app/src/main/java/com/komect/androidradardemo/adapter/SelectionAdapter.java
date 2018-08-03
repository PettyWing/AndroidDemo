package com.komect.androidradardemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komect.androidradardemo.R;
import com.komect.androidradardemo.bean.SelectionBean;
import com.komect.androidradardemo.databinding.ListSelectionItemBinding;
import com.komect.androidradardemo.widget.RadioGridView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xieyusheng on 2018/7/23.
 */

public class SelectionAdapter extends BaseJavaBeanAdapter<SelectionBean> implements RadioGridView.OnRadioItemClickListener {

    private static final String TAG = "SelectionAdapter";
    ListSelectionItemBinding binding;
    private Context context;
    private static final String ITEM_TITLE = "item_title";
    private static final String ITEM_ID = "item_id";
    private static final String ROW_ID = "row_id";
    private List<Integer> result = new ArrayList<>();

    public SelectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void setData(Collection<SelectionBean> data) {
        super.setData(data);
        initList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.list_selection_item, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListSelectionItemBinding) convertView.getTag();
        }

        SelectionBean bean = getItem(position);
        GridviewItemAdapter saImageItems = new GridviewItemAdapter(context,
                getGridViewData(position, bean.getDatas()),
                R.layout.item_selection,
                new String[]{ITEM_TITLE},
                new int[]{R.id.item_text});
        saImageItems.setSelectedPosition(1);
        // 设置GridView的adapter。GridView继承于AbsListView。
        binding.container.setAdapter(saImageItems);
        Log.d(TAG, "getView: " + result.get(position));
        binding.container.setOnRadioItemClickListener(this);
        binding.title.setText(bean.getTitle());

        binding.executePendingBindings();
        return convertView;
    }

    @Override
    public void onItemClick(Object object, int position) {
        HashMap<String, Object> item = (HashMap<String, Object>) object;
        result.set((int) item.get(ROW_ID), (int) item.get(ITEM_ID));
    }

    private List<HashMap<String, Object>> getGridViewData(int position, List<SelectionBean.Data> data) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < data.size(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(ITEM_TITLE, data.get(i).getTitle());
            map.put(ITEM_ID, data.get(i).getId());
            map.put(ROW_ID, position);
            list.add(map);
        }

        return list;
    }

    private void initList() {
        for (int i = 0; i < getCount(); i++) {
            result.add(0);
        }
    }


    public List<Integer> getResult() {
        return result;
    }
}
