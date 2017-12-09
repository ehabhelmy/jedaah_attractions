package com.example.ehab.japroject.ui.Home;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.ui.Base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @Inject
    HomePresenter presenter;

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
