package com.spade.ja.ui.Home.events;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.events.all_events.AllEventsFragment;
import com.spade.ja.ui.Home.events.nearby_events.NearByEventsFragment;
import com.spade.ja.ui.Home.events.today_events.TodayEventsFragment;
import com.spade.ja.ui.Home.events.week_events.WeekEventsFragment;

/**
 * Created by ehab on 12/15/17.
 */

public class EventsViewPagerAdapter extends FragmentStatePagerAdapter {

    private BaseFragment currentFragment;

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public EventsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AllEventsFragment();
        }else if (position == 1){
            return new TodayEventsFragment();
        }else if (position == 2){
            return new WeekEventsFragment();
        }else if (position == 3){
            return new NearByEventsFragment();
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (currentFragment != object) {
            currentFragment = (BaseFragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return JaApplication.getContext().getString(R.string.allevents);
            case 1 :
                return JaApplication.getContext().getString(R.string.todayEvents);
            case 2 :
                return JaApplication.getContext().getString(R.string.ThisWeekEvents);
            case 3 :
                return JaApplication.getContext().getString(R.string.NearByEvents);
            default:
                return null;
        }
    }
}