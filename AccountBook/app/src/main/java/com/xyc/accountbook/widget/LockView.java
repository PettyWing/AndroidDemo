package com.xyc.accountbook.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.xyc.accountbook.R;
import com.xyc.accountbook.databinding.ViewLockBinding;

/**
 * Created by xieyusheng on 2018/8/20.
 */

public class LockView extends LinearLayout {

    ViewLockBinding binding;
    private Context context;
    private Animation animation;
    private OnLockOpenListener listener;

    public LockView(Context context) {
        this(context, null);
    }

    public LockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        binding = ViewLockBinding.inflate(LayoutInflater.from(context));
        addView(binding.getRoot());
        initAnim();
    }

    private void initAnim() {
        animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onLockOpen();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void setOnLockOpenListener(OnLockOpenListener listener) {
        this.listener = listener;
    }

    public void open() {
        binding.topView.startAnimation(animation);
    }

    public interface OnLockOpenListener {
        void onLockOpen();
    }

}
