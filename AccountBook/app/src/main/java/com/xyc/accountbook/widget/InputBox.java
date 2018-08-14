package com.xyc.accountbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xyc.accountbook.R;
import com.xyc.accountbook.databinding.InputBoxBinding;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public class InputBox extends LinearLayout implements View.OnClickListener {

    private InputBoxBinding binding;
    private boolean deletable = true; //默认所有内容都可以删除
    private OnItemDeleteClickListener listener;

    public InputBox(Context context) {
        this(context, null);
    }

    public InputBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener listener) {
        this.listener = listener;
    }

    private void initView(Context context, AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.input_box, this, false);
        binding.clear.setOnClickListener(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputBox);
        String title = typedArray.getString(R.styleable.InputBox_title);
        deletable = TextUtils.isEmpty(title);
        typedArray.recycle();
        setTitle(title);

        this.addView(binding.getRoot());
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        binding.key.setText(title);
        // 设置输入框不可点击
        binding.key.setFocusable(false);
        binding.key.setFocusableInTouchMode(false);
        binding.notifyChange();
    }

    public String getTitle() {
        return binding.key.getText().toString();
    }

    public String getValue() {
        return binding.value.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(binding.value.getText().toString())) {
            // 清除内容
            binding.value.setText("");
            binding.notifyChange();
        } else if (deletable) {
            if (!TextUtils.isEmpty(binding.key.getText().toString())) {
                // 清除key
                binding.key.setText("");
                binding.notifyChange();
            } else {
                // 清除整一行
                listener.onItemDelete();
            }
        }

    }

    public interface OnItemDeleteClickListener {
        void onItemDelete();
    }
}
