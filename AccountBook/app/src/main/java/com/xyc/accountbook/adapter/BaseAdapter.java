package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by xieyusheng on 2018/8/13.
 */

abstract class BaseAdapter<Bean> extends RecyclerView.Adapter{

    private ArrayList <Bean> data = new ArrayList<>();

    public void setData(Collection<Bean> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(Collection<Bean> data) {
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void removeData(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void removeAllData() {
        data.clear();
        notifyDataSetChanged();
    }

    public Bean getItem(int i) {
        if (data.size() <= i || i < 0) {
            return null;
        } else {
            return data.get(i);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
