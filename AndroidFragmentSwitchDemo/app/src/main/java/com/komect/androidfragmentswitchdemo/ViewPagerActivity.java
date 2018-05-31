package com.komect.androidfragmentswitchdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.komect.androidfragmentswitchdemo.viewpagerTrans.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2018/4/12.
 */

public class ViewPagerActivity extends FragmentActivity {

    private static final String TAG = "ViewPagerActivity";
    private ViewPager viewPager1;
    private MyPagerAdapter adapter1;
    Fragment1 fragment1;
    Fragment2 fragment2;
    private List<Fragment> fragList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initData();
        initView();
    }

    private void initData() {
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        fragList = new ArrayList<>();
        fragList.add(fragment1);
        fragList.add(fragment2);

    }

    private void initView() {
        viewPager1 = (ViewPager) findViewById(R.id.vier_page1);
        adapter1 = new MyPagerAdapter(getSupportFragmentManager(), fragList);
        viewPager1.setAdapter(adapter1);
        viewPager1.setPageTransformer(true, new DepthPageTransformer());
        viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
