package com.example.ehab.japroject.ui.authentication.login;

import com.example.ehab.japroject.datalayer.pojo.response.login.LoginResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.login.Login;

import javax.inject.Inject;

/**
 * Created by ehab on 12/21/17.
 */

public class SignInPresenter extends BasePresenter<SignInContract.View> implements SignInContract.Presenter {

    private Login login;
    private BaseCallback<LoginResponse> loginResponseBaseCallback = new BaseCallback<LoginResponse>() {
        @Override
        public void onSuccess(LoginResponse model) {
            jaNavigationManager.goToHomeActivity();
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
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
        login.login(loginResponseBaseCallback,email,password);
    }

    @Override
    public void getResetPasswordCode() {

    }

    @Override
    public void resetPassword(String newPassword) {

    }

    @Override
    public void showRegisterScreen() {
        jaNavigationManager.showRegisterationScreen();
    }
}
