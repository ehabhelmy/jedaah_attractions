package com.example.ehab.japroject.ui.authentication.socialmedia;

import android.os.Bundle;

import com.example.ehab.japroject.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 12/19/17.
 */

public class SocialMediaPresenter extends BasePresenter<SocialMediaContract.View> implements SocialMediaContract.Presenter {

    @Inject
    public SocialMediaPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }

    public void showRegisterationScreen(){
        this.jaNavigationManager.showRegisterationScreen();
    }

    @Override
    public void showSignInScreen() {
        jaNavigationManager.showSignInScreen();
    }
}
