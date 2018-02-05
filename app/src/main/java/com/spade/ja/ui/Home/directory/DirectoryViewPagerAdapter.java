package com.spade.ja.ui.Home.directory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Home.directory.attractions.AttractionFragment;
import com.spade.ja.ui.Home.directory.venues.VenuesFragment;

/**
 * Created by Roma on 1/14/2018.
 */

public class DirectoryViewPagerAdapter extends FragmentStatePagerAdapter {
    public DirectoryViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new VenuesFragment();
        }else if (position == 1){
            return new AttractionFragment();
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return JaApplication.getContext().getString(R.string.venues);
            case 1 :
                return JaApplication.getContext().getString(R.string.attractions);
            default:
                return null;
        }
    }
}
