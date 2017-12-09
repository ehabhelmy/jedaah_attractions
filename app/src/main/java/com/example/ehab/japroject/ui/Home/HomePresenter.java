package com.example.ehab.japroject.ui.Home;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    // inject proper constructor
    @Inject
    public HomePresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }
}
