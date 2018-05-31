package com.komect.androidfragmentswitchdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xieyusheng on 2018/4/12.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragList;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragList.isEmpty() ? null : fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList != null ? fragList.size() : 0;
    }
}
