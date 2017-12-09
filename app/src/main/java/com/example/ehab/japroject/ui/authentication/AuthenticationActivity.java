package com.example.ehab.japroject.ui.authentication;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.ui.Base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class AuthenticationActivity extends BaseActivity implements AuthenticationContract.View {

    @Inject
    AuthenticationPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();
        jaNavigationManager.setCurrentActivity(this);
    }

    @Override
    protected void initializeDagger() {
        JaApplication application = (JaApplication) getApplicationContext();
        application.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        //TODO : add resource file of the layout of this activity
        return 0;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
