package com.example.ehab.japroject.ui.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.example.ehab.japroject.ui.Home.HomeActivity;
import com.example.ehab.japroject.ui.Home.events.EventsFragment;
import com.example.ehab.japroject.ui.Home.explore.ExploreFragment;
import com.example.ehab.japroject.util.Constants;

/**
 * Created by ehab on 12/1/17.
 */

public class JaPortraitNavigationManager extends JaNavigationManager {


    @Override
    public void showExploreScreen() {
        ExploreFragment exploreFragment = (ExploreFragment) fragmentManager.findFragmentByTag(EXPLORE);
        if (exploreFragment == null) {
            exploreFragment = new ExploreFragment();
        }
        replaceFragment(exploreFragment,false,EXPLORE);
    }

    @Override
    public void showEventsScreen() {
        EventsFragment eventsFragment = (EventsFragment) fragmentManager.findFragmentByTag(EVENTS);
        if (eventsFragment == null) {
            eventsFragment = new EventsFragment();
        }
        replaceFragment(eventsFragment,false,EVENTS);
    }

    @Override
    public void showLocationSettings() {
        getCurrentActivity().startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),LOCATION_SETTINGS);
    }
}
