package com.spade.ja.ui.walkthrough;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.spade.ja.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = IntroFragment.newInstance(R.drawable.intro_map, R.string.location_intro, R.string.location_intro_subtitle);
                break;
            case 1:
                fragment = IntroFragment.newInstance(R.drawable.intro_calendar, R.string.calendar_intro, R.string.calendar_intro_subtitle);
                break;
            case 2:
                fragment = IntroFragment.newInstance(R.drawable.intro_attraction, R.string.attraction_intro, R.string.attraction_intro_subtitle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
