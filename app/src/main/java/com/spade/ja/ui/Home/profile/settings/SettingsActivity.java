package com.spade.ja.ui.Home.profile.settings;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ehab on 3/11/18.
 */

public class SettingsActivity extends BaseActivity implements SettingsContract.View {

    @Inject
    SettingsPresenter presenter;

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
        return R.layout.activity_settings;
    }
}
