package com.xyc.mvvmdemo.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by xieyusheng on 2018/12/24.
 */

public class ItemViewModel extends BaseViewModel{

    public String title = "dasda";

    public ItemViewModel(@NonNull Application application) {
        super(application);
    }
}
