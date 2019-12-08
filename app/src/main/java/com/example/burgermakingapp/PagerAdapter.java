package com.example.burgermakingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numofTabs;

    public PagerAdapter(FragmentManager fm, int numofTabs){
        super(fm);
        this.numofTabs = numofTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            default : return null;
        }
    }

    @Override
    public int getCount() {
        return numofTabs;
    }
}
