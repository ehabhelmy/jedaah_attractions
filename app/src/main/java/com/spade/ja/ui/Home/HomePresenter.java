package com.spade.ja.ui.Home;

import android.os.Bundle;

import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Home.explore.ExploreFragment;
import com.spade.ja.usecase.login.Token;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private Token token;
    @Inject
    public HomePresenter(Token token) {
        this.token = token;
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
    public void showSearch() {
        jaNavigationManager.showSearch();
    }

    @Override
    public void showEvents() {
        jaNavigationManager.showEventsScreen();
    }

    @Override
    public void showProfile() {
        if (token.getToken() != null) {
            jaNavigationManager.showProfileScreen();
        }else {
            jaNavigationManager.goToAuthActivity(null);
        }
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
        if (exploreFragment != null) {
            exploreFragment.hideNearByEvents();
            exploreFragment.hideNearByVenues();
            exploreFragment.hideNearByAttractions();
        }
    }

    @Override
    public <F extends BaseFragment> F getCurrentFragmentOnHome() {
        return jaNavigationManager.getCurrentFragmentOnHome();
    }


}
