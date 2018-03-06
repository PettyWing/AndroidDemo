package com.komect.androidnewlistinfodemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komect.androidnewlistinfodemo.databinding.ListItemBinding;

/**
 * Created by xieyusheng on 2018/3/6.
 */

public class ListAdapter extends BaseJavaBeanAdapter<String> {

    private ListItemBinding binding;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.list_item, viewGroup, false);
            view = binding.getRoot();
            view.setTag(binding);
        } else {
            binding = (ListItemBinding) view.getTag();
        }

        binding.tvDetail.setText(getItem(i));
        binding.executePendingBindings();
        return view;
    }
}