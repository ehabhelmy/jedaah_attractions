package com.spade.ja.ui.Home.profile;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.profile.Data;
import com.spade.ja.datalayer.pojo.response.profile.ProfileResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.login.Token;
import com.spade.ja.usecase.profile.Profile;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    private Profile profile;
    private Token token;
    private Data profileData;

    private BaseCallback<ProfileResponse> profileResponseBaseCallback = new BaseCallback<ProfileResponse>() {
        @Override
        public void onSuccess(ProfileResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    profileData = model.getData();
                    getView().updateProfileFragment(model.getData());
                }
            }
        }

        @Override
        public void onError(String message) {
            jaNavigationManager.goToAuthActivity(null);
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
        jaNavigationManager.goToAuthActivity(null);
    }

    @Override
    public void editProfile() {
        jaNavigationManager.openEditActivity(profileData);
    }
}
