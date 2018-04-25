package com.example.parktaeim.mentor_to_mentee.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> tabTitles;
    ArrayList<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        tabTitles = new ArrayList<>();
        fragments = new ArrayList<>();
    }
    public void addElement(String tabTitle, Fragment fragment) {
        this.tabTitles.add(tabTitle);
        this.fragments.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
