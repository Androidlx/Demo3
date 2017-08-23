package com.example.lixin.yuekaotestdemo3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hua on 2017/8/23.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    List<TabInfo.DataBean> mdata;
    private FragmentManager mFragmentManager;
    public MyPagerAdapter(FragmentManager fm, List<TabInfo.DataBean> data) {
        super(fm);
        mFragmentManager = fm;
        mdata = data;
    }

    @Override
    public Fragment getItem(int position) {
        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mdata.get(position).getName();
    }
}
