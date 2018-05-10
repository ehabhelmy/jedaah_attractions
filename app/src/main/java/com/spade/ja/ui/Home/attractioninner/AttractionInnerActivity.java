package com.spade.ja.ui.Home.attractioninner;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;

import javax.inject.Inject;

/**
 * Created by ehab on 3/9/18.
 */

public class AttractionInnerActivity extends BaseActivity implements AttractionInnerContract.View {

    @Inject
    AttractionInnerPresenter presenter;

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
        return R.layout.activity_attraction_inner;
    }

    @Override
    public void onBackPressed() {
        if (presenter.getCurrentFragment() instanceof EventOrderSuccessFragment) {
            finish();
        }else {
            super.onBackPressed();
        }
    }
}
