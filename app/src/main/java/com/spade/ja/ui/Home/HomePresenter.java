package com.spade.ja.ui.Home;

import android.os.Bundle;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Home.explore.ExploreFragment;

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
        jaNavigationManager.showProfileScreen();
    }

    @Override
    public void showDirectory() {
        jaNavigationManager.showDirectoryScreen();
    }

    @Override
    public void locationIsEnabled() {
        jaNavigationManager.getCurrentFragmentOnHome().locationIsEnabled();
    }

    @Override
    public void hideNearByEvents() {
        ExploreFragment exploreFragment = jaNavigationManager.getCurrentFragmentOnHome();
        exploreFragment.hideNearByEvents();
        exploreFragment.hideNearByVenues();
    }

    @Override
    public <F extends BaseFragment> F getCurrentFragmentOnHome() {
        return jaNavigationManager.getCurrentFragmentOnHome();
    }


}
