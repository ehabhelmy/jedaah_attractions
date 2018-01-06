package com.example.ehab.japroject.ui.authentication;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.login.Token;
import com.example.ehab.japroject.usecase.sociallogin.SocialLogin;

import javax.inject.Inject;

/**
 * Created by ehab on 12/19/17.
 */

public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    private SocialLogin socialLogin;
    private Token token;
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
    public AuthenticationPresenter(SocialLogin login,Token token) {
        this.socialLogin = login;
        this.token = token;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        jaNavigationManager.showSocialMediaScreen();
    }

    @Override
    public void unSubscribe() {
        socialLogin.unSubscribe();
    }

    @Override
    public void socialLogin(String facebookId,String googleId, String email) {
        socialLogin.socialLogin(facebookId,googleId,email,loginResponseBaseCallback);
    }

    @Override
    public void goToHomeActivity() {
        token.clearToken();
        jaNavigationManager.goToHomeActivity();
    }
}
