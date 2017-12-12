package com.example.ehab.japroject.ui.Home;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;

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

    }

    @Override
    public void showProfile() {

    }
}
