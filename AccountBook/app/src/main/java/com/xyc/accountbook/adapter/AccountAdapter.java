package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyc.accountbook.R;
import com.xyc.accountbook.bean.AccountInfo;

import java.util.List;

/**
 * Created by xieyusheng on 2018/7/30.
 */

public class AccountAdapter extends RecyclerView.Adapter {

    private static final String TAG = "AccountAdapter";
    private List<AccountInfo> mList;
    private LayoutInflater mLayoutInflater;
    private OnAccountClickListener mOnClickListener;

    public AccountAdapter(LayoutInflater layoutInflater, List<AccountInfo> list) {
        this.mList = list;
        this.mLayoutInflater = layoutInflater;
    }

    public void setData(List<AccountInfo> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setOnAccountClickListener(OnAccountClickListener listener) {
        mOnClickListener = listener;
    }

    private AccountInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(mLayoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccountInfo target = getItem(position);
        if (holder instanceof ContactsViewHolder) {
            ((ContactsViewHolder) holder).bindBean(target);
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ContactsViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void bindBean(final AccountInfo info) {
            title.setText(info.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onItemClicked(info);
                }
            });
        }
    }

    public interface OnAccountClickListener {
        void onItemClicked(AccountInfo bean);
    }
}
