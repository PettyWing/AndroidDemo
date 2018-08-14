package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyc.accountbook.R;
import com.xyc.accountbook.bean.AccountDetail;

/**
 * Created by xieyusheng on 2018/8/13.
 */

public class ADDialogAdapter extends BaseAdapter<AccountDetail> {

    private LayoutInflater mLayoutInflater;
    private OnCopyClickListener mOnClickListener;

    public ADDialogAdapter(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public void setOnCopyClickListener(OnCopyClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DialogViewHolder(mLayoutInflater.inflate(R.layout.item_dialog, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccountDetail target = getItem(position);
        if (holder instanceof ADDialogAdapter.DialogViewHolder) {
            ((ADDialogAdapter.DialogViewHolder) holder).bindBean(target);
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }

    private class DialogViewHolder extends RecyclerView.ViewHolder {

        private TextView key;
        private TextView value;
        private ImageView copy;

        public DialogViewHolder(View itemView) {
            super(itemView);
            key = (TextView) itemView.findViewById(R.id.tv_key);
            value = (TextView) itemView.findViewById(R.id.tv_value);
            copy = (ImageView) itemView.findViewById(R.id.bt_copy);
        }

        public void bindBean(final AccountDetail detail) {
            key.setText(detail.getTitle() + "：");
            value.setText(detail.getValue());
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onCopyClick(value.getText().toString());
                }
            });
        }
    }

    public interface OnCopyClickListener {
        void onCopyClick(String value);
    }
}
