package com.komect.androidradardemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.komect.androidradardemo.R;

/**
 * Created by xieyusheng on 2018/7/20.
 */

public class RadioGridView extends GridView implements AdapterView.OnItemClickListener {

    private static final String TAG = "RadioGridView";
    private int currentPosition = 0;
    private OnRadioItemClickListener mListener;
    private int mBgImageSelected;
    private int mBgImageUnselected;
    private int mTvSelected;
    private int mTvUnselected;

    public RadioGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    //可以在xml文件直接设置item背景，是不是很方便
    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadioGridView);
        mBgImageSelected = typedArray.getResourceId(R.styleable.RadioGridView_bg_item_selected,
                R.drawable.bg_radius_selected);
        mBgImageUnselected = typedArray.getResourceId(R.styleable.RadioGridView_bg_item_unselected,
                R.drawable.bg_radius_unselect);
        mTvSelected = typedArray.getResourceId(R.styleable.RadioGridView_tv_item_selected,
                R.color.selectedColor);
        mTvUnselected = typedArray.getResourceId(R.styleable.RadioGridView_tv_item_unselected,
                R.color.unSelectedColor);
        typedArray.recycle();
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //一个很小的逻辑，刚开始用的for循环，感觉太low了，改为这种
        int lastPosition = currentPosition;
        currentPosition = position;
        parent.getChildAt(lastPosition).setBackgroundResource(mBgImageUnselected);//未被选择时的背景
        TextView tvLast = parent.getChildAt(lastPosition).findViewById(R.id.item_text); //未被选择时的文字
        tvLast.setTextColor(getResources().getColor(mTvUnselected));
        view.setBackgroundResource(mBgImageSelected);//被选择是的背景
        TextView tvCurrent = view.findViewById(R.id.item_text); //被选择时的文字
        tvCurrent.setTextColor(getResources().getColor(mTvSelected));
        if (mListener != null) {
            mListener.onItemClick(parent.getItemAtPosition(position), position);
        }
    }

    /**
     * 设置item点击监听器
     *
     * @param listener item点击监听器
     */
    public void setOnRadioItemClickListener(OnRadioItemClickListener listener) {
        mListener = listener;
    }

    /**
     * item点击监听器
     */
    public interface OnRadioItemClickListener {
        /**
         * @param object   存储的内容
         * @param position item 位置
         */
        void onItemClick(Object object, int position);
    }
}