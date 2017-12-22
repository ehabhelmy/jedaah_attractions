package com.example.ehab.japroject.ui.Home;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Home.explore.ExploreFragment;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }

    @Override
    public void showExplore() {
        jaNavigationManager.showExploreScreen();
    }

    @Override
    public void showEvents() {
        jaNavigationManager.showEventsScreen();
    }

    @Override
    public void showProfile() {

    }

    @Override
    public void locationIsEnabled() {
        jaNavigationManager.getCurrentFragmentOnHome().locationIsEnabled();
    }

    @Override
    public void hideNearByEvents() {
        ExploreFragment exploreFragment = jaNavigationManager.getCurrentFragmentOnHome();
        exploreFragment.hideNearByEvents();
    }
}
