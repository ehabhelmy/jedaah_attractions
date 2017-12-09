package com.example.ehab.japroject.ui.authentication;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    @Inject
    public AuthenticationPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }

    @Override
    public void skipLogin() {
        jaNavigationManager.navigateToHome(null);
    }
}
