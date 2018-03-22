package com.example.sainikhil.vasavinews.tagsdata;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TagsAdapter extends FragmentPagerAdapter{
    TabsFragment current;
    private int count;
    private final String[] tagsArray;
    public TagsAdapter(FragmentManager fm, String[] tabs) {
        super(fm);
        count = tabs.length;
        tagsArray = tabs;
    }

    @Override
    public Fragment getItem(int position) {
/*
        TabsFragment tab = new TabsFragment();
        current = tab;*/
        return new TabsFragment();
    }

    @Override
    public int getCount() {
        return count+1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "Home";
        return tagsArray[position-1];
    }
}
