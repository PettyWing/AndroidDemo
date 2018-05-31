package com.komect.androidfragmentswitchdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lsl on 2017/9/21.
 */

public class FragPagerTitleAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragList;
    private List<String> pageTitles;

    public FragPagerTitleAdapter(FragmentManager fm, List<Fragment> fragList, List<String> pageTitles) {
        super(fm);
        this.fragList = fragList;
        this.pageTitles = pageTitles;
//        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        return fragList.isEmpty() ? null : fragList.get(position);
    }


    @Override
    public int getCount() {
        return fragList != null ? fragList.size() : 0;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }


}
