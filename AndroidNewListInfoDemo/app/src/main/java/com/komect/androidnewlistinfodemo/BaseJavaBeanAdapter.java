package com.komect.androidnewlistinfodemo;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 对ListView的Adapter进行一层封装,以范型的方式支持JavaBean数据
 * Author by hf
 * Create on 16/8/15
 */
abstract class BaseJavaBeanAdapter<Bean> extends BaseAdapter {
    protected final ArrayList<Bean> data = new ArrayList<>();


    public ArrayList<Bean> getData() {
        return data;
    }

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

    public void addData(Bean bean) {
        data.add(bean);
        notifyDataSetChanged();
    }

    public void addDataOnHead(Bean bean) {
        data.add(0, bean);
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

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Bean getItem(int i) {
        if (data.size() <= i || i < 0) {
            return null;
        } else {
            return data.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


}
