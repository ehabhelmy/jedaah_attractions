package com.spade.ja.ui.Home.eventsinner;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseActivity;
import com.spade.ja.ui.Home.eventsinner.eventcheckout.EventOrderFragment;
import com.spade.ja.ui.Home.eventsinner.eventdetails.EventInnerFragment;
import com.spade.ja.ui.Home.eventsinner.eventordersuccess.EventOrderSuccessFragment;

import javax.inject.Inject;

/**
 * Created by ehab on 12/22/17.
 */

public class EventInnerActivity extends BaseActivity implements EventInnerActivityContract.View {

    @Inject
    EventInnerActivityPresenter presenter;

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
        return R.layout.activity_inner_event;
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
