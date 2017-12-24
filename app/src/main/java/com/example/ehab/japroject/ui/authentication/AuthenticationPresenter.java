package com.example.ehab.japroject.ui.authentication;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.login.Login;

import javax.inject.Inject;

/**
 * Created by ehab on 12/19/17.
 */

public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    private Login login;
    private BaseCallback<LoginResponse> loginResponseBaseCallback = new BaseCallback<LoginResponse>() {
        @Override
        public void onSuccess(LoginResponse model) {
            jaNavigationManager.goToHomeActivity();
        }

        @Override
        public void onError(String message) {
            jaNavigationManager.getCurrentFragmentOnAuth().onErrorAuth(message);
        }
    };

    @Inject
    public AuthenticationPresenter(Login login) {
        this.login = login;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        jaNavigationManager.showSocialMediaScreen();
    }

    @Override
    public void unSubscribe() {
        login.unSubscribe();
    }

    @Override
    public void login(String id, String password) {
        login.login(loginResponseBaseCallback,id,password);
    }

    @Override
    public void goToHomeActivity() {
        jaNavigationManager.goToHomeActivity();
    }
}
