package com.example.ehab.japroject.ui.splash;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.ui.Base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ehab on 12/2/17.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter presenter;

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
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
