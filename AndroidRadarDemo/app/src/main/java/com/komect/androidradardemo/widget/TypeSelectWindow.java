package com.komect.androidradardemo.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.komect.androidradardemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BDXK on 2017/11/9.
 */

public class TypeSelectWindow extends PopupWindow {

    private Activity activity;
    SelectionView contentView;
    LinearLayout selectView;

    public TypeSelectWindow(Activity activity) {
        this.activity = activity;
        initDatas();
        initView();
    }

    private void initView() {
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        contentView = new SelectionView(activity);
        selectView = contentView.findViewById(R.id.layout_header);
        this.setContentView(contentView);//设置布局
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setAnimationStyle(R.style.SelectPopupWindow);
        this.setOutsideTouchable(true);
        this.setFocusable(true);
    }

    /**
     * 模拟数据
     */
    List<String> listDatas = new ArrayList<>();

    private void initDatas() {
        listDatas.clear();
        for (int i = 0; i < 10; i++) {
            listDatas.add("menu" + i);
        }
    }

    //显示
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent, 0, 0);
            //执行进入动画，这里主要是执行列表下滑，背景变半透明在setAnimationStyle(R.style.SelectPopupWindow);中实现
//            AnimationUtil.createAnimation(true, contentView, selectView, null);
        } else {
            dismissPopup();
        }
    }

    //消失
    public void dismissPopup() {
        super.dismiss();// 调用super.dismiss(),如果直接dismiss()会一直会调用下面的dismiss()
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //执行推出动画，列表上滑退出，同时背景变透明
//        AnimationUtil.createAnimation(false, contentView, selectView, new AnimationUtil.AnimInterface() {
//            @Override
//            public void animEnd() {
//                dismissPopup();//动画执行完毕后消失
//            }
//        });
    }
}
