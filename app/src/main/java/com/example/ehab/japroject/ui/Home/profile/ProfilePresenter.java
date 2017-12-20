package com.example.ehab.japroject.ui.Home.profile;

import com.example.ehab.japroject.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    @Inject
    public ProfilePresenter() {
    }

    @Override
    public void unSubscribe() {

    }
}
