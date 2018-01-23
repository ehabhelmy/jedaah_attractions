package com.example.ehab.japroject.ui.Home.venueinner;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ehab on 1/20/18.
 */

public class VenueInnerActivity extends BaseActivity implements VenueInnerContract.View {

    @Inject
    VenueInnerPresenter presenter;

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

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
        return R.layout.activity_venue_inner;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.goToHomeActivity();
    }
}
