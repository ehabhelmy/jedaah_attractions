package com.example.ehab.japroject.ui.Home.eventsinner.eventordersuccess;

import com.example.ehab.japroject.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 1/10/18.
 */

public class EventOrderSuccessPresenter extends BasePresenter<EventOrderSuccessContract.View> implements EventOrderSuccessContract.Presenter {

    @Inject
    public EventOrderSuccessPresenter() {
    }

    @Override
    public void bookMore() {
        jaNavigationManager.goBackToOrderView();
    }
}
