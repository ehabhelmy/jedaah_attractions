package com.example.ehab.japroject.ui.Home.events;

import com.example.ehab.japroject.ui.Base.BasePresenter;

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
}
