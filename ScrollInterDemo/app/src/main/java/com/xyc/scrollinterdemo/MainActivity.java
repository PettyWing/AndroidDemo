package com.xyc.scrollinterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 滑动冲突：使用BadViewPager
 * 外部拦截法：使用BadViewPagerFix1
 * 内部拦截法：使用
 */
public class MainActivity extends AppCompatActivity {

    private BadViewPagerFix2 mViewPager;
    private ArrayList<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData(true);
    }

    protected void initViews() {
        mViewPager = findViewById(R.id.viewpager);
        mViews = new ArrayList<>();
    }

    protected void initData(final boolean isListView) {
        for (int i = 0; i < 4; i++) {
            View view;
            if (isListView) {
                //初始化ListView
                ListViewFix2 listView = new ListViewFix2(this);
                final ArrayList<String> datas = new ArrayList<>();
                //初始化数据，datas ,data0 ...data49
                for (int j = 0; j < 50; j++) {
                    datas.add(i + "data" + j);
                }
                //初始化adapter
                ArrayAdapter<String> adapter = new ArrayAdapter<>
                        (this, android.R.layout.simple_list_item_1, datas);
                //设置adapter
                listView.setAdapter(adapter);
                //将ListView赋值给当前View
                view = listView;
            } else {
                //初始化TextView
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView.setText("view" + i);
                //将TextView赋值给当前View
                view = textView;
            }
            //将当前View添加到ViewPager的ViewList中去
            mViews.add(view);
        }
        //设置ViewPager的Adapter
        mViewPager.setAdapter(new BasePagerAdapter<>(mViews));
    }
}
