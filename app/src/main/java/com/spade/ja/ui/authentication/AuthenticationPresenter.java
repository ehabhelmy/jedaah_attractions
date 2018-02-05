package com.spade.ja.ui.authentication;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.login.Token;
import com.spade.ja.usecase.sociallogin.SocialLogin;

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
