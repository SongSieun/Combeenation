package com.sesong.combeenation.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sesong.combeenation.Fragment.BeautyFragment;
import com.sesong.combeenation.Fragment.FoodFragment;
import com.sesong.combeenation.Fragment.TravelFragment;

import java.util.ArrayList;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mData;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        mData = new ArrayList<>();
        mData.add(new FoodFragment());
        mData.add(new BeautyFragment());
        mData.add(new TravelFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabName = null;
        switch (position) {
            case 0:
                tabName = "Food";
                break;
            case 1:
                tabName = "Travel";
                break;
            case 2:
                tabName = "Beauty";
                break;
        }
        return tabName;
    }
}
