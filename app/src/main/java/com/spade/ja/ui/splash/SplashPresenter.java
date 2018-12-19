package com.spade.ja.ui.splash;

import android.os.Bundle;
import android.os.Handler;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.usecase.login.Token;
import com.spade.ja.usecase.walkthrough.WalkThroughUseCase;

import javax.inject.Inject;

/**
 * Created by ehab on 12/27/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter{

    private Token token;
    private WalkThroughUseCase walkThroughUseCase;

    @Inject
    public SplashPresenter(Token token,WalkThroughUseCase walkThroughUseCase) {
        this.token = token;
        this.walkThroughUseCase = walkThroughUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        new Handler().postDelayed(() -> {
            if (token.getToken() == null) {
                if (walkThroughUseCase.isFirstInstall()){
                    jaNavigationManager.goToWalkthrough();
                }else {
                    jaNavigationManager.goToAuthActivity(null);
                }
            }else {
                jaNavigationManager.goToHomeActivity();
            }
        },3000);
    }
}
