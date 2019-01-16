package com.xyc.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.xyc.mvvmdemo.BR;
import com.xyc.mvvmdemo.R;
import com.xyc.mylibrary.TestActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by xieyusheng on 2018/12/24.
 */

public class MainViewModel extends BaseViewModel {

    // textView
    public String title = "xiexei";
    // imageView
    public String imgUrl = "http://img0.imgtn.bdimg.com/it/u=2183314203,562241301&fm=26&gp=0.jpg";

    //给RecyclerView添加items
    public final ObservableList<ItemViewModel> observableList = new ObservableArrayList<>();
    //给RecyclerView添加ItemBinding
    public final ItemBinding<ItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_list);

    public MainViewModel(@NonNull Application application) {
        super(application);
        for (int i = 0; i < 3; i++) {
            observableList.add(new ItemViewModel(application));
        }
    }

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(TestActivity.class);
//            Toast.makeText(getApplication(), "xiexie你", Toast.LENGTH_SHORT).show();
        }
    });
}
