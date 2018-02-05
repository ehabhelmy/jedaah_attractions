package com.spade.ja.ui.splash;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ehab on 12/27/17.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
