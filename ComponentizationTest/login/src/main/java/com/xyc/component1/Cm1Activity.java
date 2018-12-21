package com.xyc.component1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by xieyusheng on 2018/12/20.
 */

@Route(path = "/account/login")
public class Cm1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cm1);
    }

    public void onShareClick(View view){
        ARouter.getInstance().build("/share/share").withString("share_content", "分享数据到微博").navigation();
    }
}
