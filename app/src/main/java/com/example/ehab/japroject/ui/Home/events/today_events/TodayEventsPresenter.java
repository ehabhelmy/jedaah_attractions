package com.example.ehab.japroject.ui.Home.events.today_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.todayevents.TodayEvents;

import javax.inject.Inject;

/**
 * Created by Romisaa on 12/16/2017.
 */

public class TodayEventsPresenter extends BasePresenter<TodayEventsContract.View> implements TodayEventsContract.Presenter {

    private TodayEvents todayEvents;

    private BaseCallback<EventsResponse> todayEventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupTodayEvents(EventsAdapter.convertIntoEventUi(model.getData()));
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                getView().showError(message);
            }
        }
    };

    @Inject
    public TodayEventsPresenter(TodayEvents todayEvents) {
        this.todayEvents = todayEvents;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        this.todayEvents.getTodayEvents(todayEventsResponseBaseCallback,true);
    }

    @Override
    public void unSubscribe() {
        this.todayEvents.unSubscribe();
    }

    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }
}
