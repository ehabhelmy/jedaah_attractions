package com.spade.ja.ui.Home.events;

import com.spade.ja.ui.Base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by ehab on 12/15/17.
 */

public class EventsPresenter extends BasePresenter<EventsContract.View> implements EventsContract.Presenter {

    @Inject
    public EventsPresenter() {
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void openFilterEvents() {
        jaNavigationManager.openFilterEvents();
    }
}
