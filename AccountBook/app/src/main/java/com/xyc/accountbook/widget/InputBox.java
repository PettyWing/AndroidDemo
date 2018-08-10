package com.xyc.accountbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.xyc.accountbook.R;
import com.xyc.accountbook.databinding.InputBoxBinding;

/**
 * Created by xieyusheng on 2018/8/3.
 */

public class InputBox extends LinearLayout {

    private InputBoxBinding binding;

    public InputBox(Context context) {
        this(context, null);
    }

    public InputBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.input_box, this, false);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputBox);
        String title = typedArray.getString(R.styleable.InputBox_title);
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
}
