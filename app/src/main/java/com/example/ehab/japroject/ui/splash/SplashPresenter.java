package com.example.ehab.japroject.ui.splash;

import android.os.Bundle;
import android.os.Handler;

import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.usecase.login.Token;

import javax.inject.Inject;

/**
 * Created by ehab on 12/27/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter{

    private Token token;

    @Inject
    public SplashPresenter(Token token) {
        this.token = token;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        new Handler().postDelayed(() -> {
            if (token.getToken() == null) {
                jaNavigationManager.goToAuthActivity(null);
            }else {
                jaNavigationManager.goToHomeActivity();
            }
        },3000);
    }
}
