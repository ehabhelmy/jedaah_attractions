package com.example.ehab.japroject.ui.Home.profile;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.profile.ProfileResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.login.Token;
import com.example.ehab.japroject.usecase.profile.Profile;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    private Profile profile;
    private Token token;

    private BaseCallback<ProfileResponse> profileResponseBaseCallback = new BaseCallback<ProfileResponse>() {
        @Override
        public void onSuccess(ProfileResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().updateProfileFragment(model.getData());
                }
            }
        }

        @Override
        public void onError(String message) {
            jaNavigationManager.goToAuthActivity();
            //getView().showError(message);
        }
    };

    @Inject
    public ProfilePresenter(Profile profile,Token token) {
        this.profile = profile;
        this.token = token;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        profile.getProfile(profileResponseBaseCallback, true);
    }

    @Override
    public void unSubscribe() {
        profile.unSubscribe();
    }

    @Override
    public void logOut() {
        token.clearToken();
        jaNavigationManager.goToAuthActivity();
    }
}
