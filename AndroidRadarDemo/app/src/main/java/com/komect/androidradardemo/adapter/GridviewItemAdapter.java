package com.komect.androidradardemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.komect.androidradardemo.R;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyusheng on 2018/7/24.
 */

public class GridviewItemAdapter extends SimpleAdapter{

    private View v;

    public GridviewItemAdapter(Context context, List<? extends Map<String, ?>> data,
                          int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    private int selectedPosition = 0;// 选中的位置
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        v = super.getView(position, convertView, parent);
        if (position == selectedPosition) {
            v.setBackgroundResource(R.drawable.bg_radius_selected);
        }
        else{
            v.setBackgroundResource(R.drawable.bg_radius_unselect);
        }
        return v;
    }

}
