package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyc.accountbook.R;

/**
 * Created by xieyusheng on 2018/8/14.
 */

public class KeyboardAdapter extends BaseAdapter<String> {

    private LayoutInflater mLayoutInflater;
    private OnKeyboardItemClickListener mOnClickListener;

    public KeyboardAdapter(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public void setOnKeyboardItemClickListener(OnKeyboardItemClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KeyboardAdapter.KeyboardViewHolder(mLayoutInflater.inflate(R.layout.keyboard_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String value = getItem(position);
        if (holder instanceof KeyboardAdapter.KeyboardViewHolder) {
            ((KeyboardAdapter.KeyboardViewHolder) holder).bindBean(value, position);
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }

    private class KeyboardViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public KeyboardViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }

        public void bindBean(final String value, final int position) {
            text.setText(value);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 9) {
                        mOnClickListener.onBackClicked();
                    } else if (position == 11) {
                        mOnClickListener.onDeleteClicked();
                    } else {
                        mOnClickListener.onAddClicked(value);
                    }
                }
            });
        }
    }

    public interface OnKeyboardItemClickListener {
        void onAddClicked(String value);

        void onDeleteClicked();

        void onBackClicked();
    }
}
