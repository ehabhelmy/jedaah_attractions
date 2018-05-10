package com.spade.ja.ui.authentication.foretpassword.resetpassword;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.navigation.JaNavigationManager;
import com.spade.ja.usecase.resetpassword.ResetPassword;

import javax.inject.Inject;

public class ResetPasswordPresenter extends BasePresenter<ResetPasswordContract.View> implements ResetPasswordContract.Presenter {

    private ResetPassword resetPassword;

    private String code;

    private BaseCallback<ContactUsResponse> contactUsResponseBaseCallback = new BaseCallback<ContactUsResponse>() {
        @Override
        public void onSuccess(ContactUsResponse model) {
            if (isViewAlive.get()){
                getView().showSuccess(model.getData().getMsg());
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    @Inject
    public ResetPasswordPresenter(ResetPassword resetPassword) {
        this.resetPassword = resetPassword;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (extras != null){
            code = extras.getString(JaNavigationManager.RESETCODE);
        }
    }

    @Override
    public void resetPassword(String password) {
        resetPassword.resetPassword(code,password,contactUsResponseBaseCallback);
    }

    @Override
    public void goToLogin() {
        jaNavigationManager.showSignInScreen();
    }

    @Override
    public void unSubscribe() {
        resetPassword.unSubscribe();
    }
}
