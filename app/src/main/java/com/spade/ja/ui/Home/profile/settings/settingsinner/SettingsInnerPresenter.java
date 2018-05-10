package com.spade.ja.ui.Home.profile.settings.settingsinner;

import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.usecase.login.Token;

import javax.inject.Inject;

/**
 * Created by ehab on 3/11/18.
 */

public class SettingsInnerPresenter extends BasePresenter<SettingsInnerContract.View> implements SettingsInnerContract.Presenter {

    private Token token;
    @Inject
    public SettingsInnerPresenter(Token token) {
        this.token = token;
    }


    @Override
    public void openEmail() {
        jaNavigationManager.openEmail();
    }

    @Override
    public void openAboutScreen() {
        jaNavigationManager.openAboutScreen();
    }

    @Override
    public void openContactUs() {
        jaNavigationManager.openContactUs();
    }

    @Override
    public void logOut() {
        token.clearToken();
        jaNavigationManager.goToAuthActivity(null);
    }

    @Override
    public void restartApp() {
        jaNavigationManager.restartApp();
    }

}
