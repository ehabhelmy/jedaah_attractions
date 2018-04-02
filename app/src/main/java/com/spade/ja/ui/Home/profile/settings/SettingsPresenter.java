package com.spade.ja.ui.Home.profile.settings;

import android.os.Bundle;

import com.spade.ja.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 3/11/18.
 */

public class SettingsPresenter extends BasePresenter<SettingsContract.View> implements SettingsContract.Presenter {

    @Inject
    public SettingsPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        jaNavigationManager.showSettingsInner();
    }
}
