package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyc.accountbook.R;
import com.xyc.accountbook.bean.AccountInfo;

/**
 * Created by xieyusheng on 2018/7/30.
 */

public class AccountAdapter extends BaseAdapter<AccountInfo> {

    private static final String TAG = "AccountAdapter";
    private LayoutInflater mLayoutInflater;
    private OnAccountClickListener mOnClickListener;

    public AccountAdapter(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public void setOnAccountClickListener(OnAccountClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(mLayoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccountInfo target = getItem(position);
        if (holder instanceof ContactsViewHolder) {
            ((ContactsViewHolder) holder).bindBean(target, position);
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }

    private class ContactsViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView account;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            account = (TextView) itemView.findViewById(R.id.tv_account);
        }

        public void bindBean(final AccountInfo info, final int position) {
            title.setText(info.getName());
            account.setText(info.getAccount());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onItemClicked(info);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnClickListener.onItemLongClicked(info, position);
                    return true;
                }
            });
        }
    }

    public interface OnAccountClickListener {
        void onItemClicked(AccountInfo bean);

        void onItemLongClicked(AccountInfo bean, int position);
    }
}
