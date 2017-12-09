package com.example.ehab.japroject.ui.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ehab on 12/6/17.
 */

public class BasePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<ViewPagerFragmentFactory.ViewPagerFragment> pages;


    public ArrayList<ViewPagerFragmentFactory.ViewPagerFragment> getPages() {
        return pages;
    }

    public void setPages(ArrayList<ViewPagerFragmentFactory.ViewPagerFragment> pages) {
        this.pages = pages;
    }

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragmentFactory.getFragmentByName(pages.get(position));
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
