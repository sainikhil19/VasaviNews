package com.example.sainikhil.vasavinews.postdata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                // Your current main fragment showing how to send arguments to fragment
                TitleFragment myFragment = new TitleFragment();
                Bundle data = new Bundle();
                data.putInt("current_page", position + 1);
                myFragment.setArguments(data);
                return myFragment;
            case 1:
                // Calling a Fragment without sending arguments
                return new ImageFragment();
            case 2:
                // Calling a Fragment without sending arguments
                return new TagsFragment();
            case 3:
                // Calling a Fragment without sending arguments
                return new PreviewFragment();
            case 4:
                return new CompletedFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
