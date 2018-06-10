package com.spade.ja.ui.Home.profile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spade.ja.R;
import com.spade.ja.ui.Home.profile.liked_directory.LikedDirectoryFragment;
import com.spade.ja.ui.Home.profile.liked_events.LikedEventsFragment;
import com.spade.ja.ui.Home.profile.my_tickets.TicketsFragment;

import java.util.Locale;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class ProfileViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public ProfileViewPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    private static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    private static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    @Override
    public Fragment getItem(int position) {
        if (isRTL()){
            if (position == 0) {
                return new LikedDirectoryFragment();
            } else if (position == 1) {
                return new LikedEventsFragment();
            } else {
                return new TicketsFragment();
            }
        }else {
            if (position == 0) {
                return new TicketsFragment();
            } else if (position == 1) {
                return new LikedEventsFragment();
            } else {
                return new LikedDirectoryFragment();
            }
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (isRTL()){
            switch (position) {
                case 0:
                    return context.getString(R.string.liked_directory);
                case 1:
                    return context.getString(R.string.liked_events);
                case 2:
                    return context.getString(R.string.tickets);
                default:
                    return null;
            }
        }else {
            switch (position) {
                case 0:
                    return context.getString(R.string.tickets);
                case 1:
                    return context.getString(R.string.liked_events);
                case 2:
                    return context.getString(R.string.liked_directory);
                default:
                    return null;
            }
        }
    }

}
