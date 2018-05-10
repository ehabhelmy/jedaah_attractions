package com.spade.ja.ui.authentication.login;

import com.spade.ja.datalayer.pojo.response.login.LoginResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.login.Login;

import javax.inject.Inject;

/**
 * Created by ehab on 12/21/17.
 */

public class SignInPresenter extends BasePresenter<SignInContract.View> implements SignInContract.Presenter {

    private Login login;
    private BaseCallback<LoginResponse> loginResponseBaseCallback = new BaseCallback<LoginResponse>() {
        @Override
        public void onSuccess(LoginResponse model) {
            if (isViewAlive.get()){
                getView().hideLoading();
            }
            jaNavigationManager.goToHomeActivity();
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().hideLoading();
                getView().showError(message);
            }
        }
    };

    @Inject
    public SignInPresenter(Login login) {
        this.login = login;
    }

    @Override
    public void unSubscribe() {
        login.unSubscribe();
    }

    @Override
    public void login(String email, String password) {
        getView().showLoading();
        login.login(loginResponseBaseCallback,email,password);
    }

    @Override
    public void getResetPasswordCode() {
        jaNavigationManager.goToResetCode();
    }

    @Override
    public void showRegisterScreen() {
        jaNavigationManager.showRegisterationScreen();
    }
}
