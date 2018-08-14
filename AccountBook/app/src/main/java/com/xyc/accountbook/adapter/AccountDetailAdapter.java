package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyc.accountbook.R;
import com.xyc.accountbook.bean.AccountDetail;

/**
 * Created by xieyusheng on 2018/8/14.
 */

public class AccountDetailAdapter extends BaseAdapter<AccountDetail> {

    private LayoutInflater mLayoutInflater;

    public AccountDetailAdapter(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountDetailAdapter.AccountViewHolder(mLayoutInflater.inflate(R.layout.input_box, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccountDetail target = getItem(position);
        if (holder instanceof AccountDetailAdapter.AccountViewHolder) {
            ((AccountDetailAdapter.AccountViewHolder) holder).bindBean(target, position);
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }

    private class AccountViewHolder extends RecyclerView.ViewHolder implements View.OnFocusChangeListener {

        private TextView key;
        private TextView value;
        private ImageView btClear;
        private boolean isFixed;
        private int currentPosition;

        public AccountViewHolder(View itemView) {
            super(itemView);
            key = (TextView) itemView.findViewById(R.id.key);
            value = (TextView) itemView.findViewById(R.id.value);
            btClear = (ImageView) itemView.findViewById(R.id.clear);
        }

        public void bindBean(final AccountDetail detail, final int position) {
            key.setText(detail.getTitle());
            value.setText(detail.getValue());
            currentPosition = position;

            key.setOnFocusChangeListener(this);
            value.setOnFocusChangeListener(this);

            // 设置输入框是否可以点击
            isFixed = detail.isFixed();
            key.setFocusable(!isFixed);
            key.setFocusableInTouchMode(!isFixed);
            if (!isFixed) {
                // 新添加的选项添加焦点
                key.requestFocus();
            }

            btClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(value.getText().toString())) {
                        // 清除内容
                        value.setText("");
                    } else if (!isFixed) {
                        if (!TextUtils.isEmpty(key.getText().toString())) {
                            // 清除key
                            key.setText("");
                        } else {
                            // 清除整一行
                            removeData(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.key:
                    if (hasFocus) {
                        key.addTextChangedListener(textWatcher);
                    } else {
                        key.removeTextChangedListener(textWatcher);
                    }
                    break;
                case R.id.value:
                    if (hasFocus) {
                        value.addTextChangedListener(textWatcher);
                    } else {
                        value.removeTextChangedListener(textWatcher);
                    }
                    break;
                default:
                    break;
            }
        }

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (key.hasFocus()) {
                    //判断当前EditText是否有焦点在，将内容添加到list中
                    getData().get(currentPosition).setTitle(s.toString());
                } else if (value.hasFocus()) {
                    getData().get(currentPosition).setValue(s.toString());
                }

            }
        };
    }
}
