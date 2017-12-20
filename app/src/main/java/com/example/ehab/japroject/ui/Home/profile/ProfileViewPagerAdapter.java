package com.example.ehab.japroject.ui.Home.profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ehab.japroject.ui.Home.profile.liked_directory.LikedDirectoryFragment;
import com.example.ehab.japroject.ui.Home.profile.liked_events.LikedEventsFragment;
import com.example.ehab.japroject.ui.Home.profile.my_tickets.TicketsFragment;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class ProfileViewPagerAdapter extends FragmentPagerAdapter {

    public ProfileViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TicketsFragment();
        } else if (position == 1) {
            return new LikedEventsFragment();
        } else {
            return new LikedDirectoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MY TICKETS";
            case 1:
                return "LIKED EVENTS";
            case 2:
                return "LIKED DIRECTORY";
            default:
                return null;
        }
    }

}
