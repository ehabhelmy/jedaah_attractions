package com.example.ehab.japroject.ui.Home.eventsinner;

import com.example.ehab.japroject.JaApplication;
import com.example.ehab.japroject.R;
import com.example.ehab.japroject.ui.Base.BaseActivity;

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
}
