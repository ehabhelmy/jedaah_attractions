package com.example.ehab.japroject.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.ehab.japroject.datalayer.pojo.User;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.splash.SplashUseCase;
import com.example.ehab.japroject.usecase.splash.SplashUseCaseInterface;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private final SplashUseCase splashUseCase;
    private BaseCallback<User> userBaseCallback = new BaseCallback<User>() {
        @Override
        public void onSuccess(User model) {
            new Handler(Looper.getMainLooper()).postDelayed((Runnable) () -> {
                jaNavigationManager.navigateToHome(model);
            }, Constants.SPLASH_DELAY);
        }

        @Override
        public void onError() {
            new Handler(Looper.getMainLooper()).postDelayed((Runnable) () -> {
                jaNavigationManager.navigateToLogin();
            }, Constants.SPLASH_DELAY);
        }
    };

    @Inject
    public SplashPresenter(SplashUseCase splashUseCase) {
        this.splashUseCase = splashUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        splashUseCase.checkUserInCache(userBaseCallback);
    }

    @Override
    public void unsubscribe() {
        splashUseCase.unSubscribe();
    }
}
