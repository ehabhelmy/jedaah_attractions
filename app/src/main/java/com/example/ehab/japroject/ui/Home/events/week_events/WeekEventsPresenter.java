package com.example.ehab.japroject.ui.Home.events.week_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.weekevents.WeekEvents;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class WeekEventsPresenter extends BasePresenter<WeekEventsContract.View> implements WeekEventsContract.Presenter{

    private WeekEvents weekEvents;

    private BaseCallback<EventsResponse> weekEventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupWeekEvents(EventsAdapter.convertIntoEventUi(model.getData()));
                } else {
                    getView().showError();
                }
            }
        }

        @Override
        public void onError() {
            if (isViewAlive.get()) {
               // getView().showError();
            }
        }
    };

    @Inject
    public WeekEventsPresenter(WeekEvents weekEvents) {
        this.weekEvents = weekEvents;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        this.weekEvents.getWeekEvents(weekEventsResponseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        this.weekEvents.unSubscribe();
    }
}
