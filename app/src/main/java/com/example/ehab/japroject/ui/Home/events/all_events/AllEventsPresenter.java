package com.example.ehab.japroject.ui.Home.events.all_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.allevents.AllEvents;

import javax.inject.Inject;

/**
 * Created by ehab on 12/16/17.
 */

public class AllEventsPresenter extends BasePresenter<AllEventsContract.View> implements AllEventsContract.Presenter {

    private AllEvents allEvents;
    private BaseCallback<EventsResponse> eventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().setupAllEvents(EventsAdapter.convertIntoEventUi(model.getData()));
                } else {
                    getView().showError();
                }
            }
        }

        @Override
        public void onError() {
            if (isViewAlive.get()){
                getView().showError();
            }
        }
    };

    @Inject
    public AllEventsPresenter(AllEvents allEvents) {
        this.allEvents = allEvents;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        allEvents.getAllEvents(eventsResponseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        allEvents.unSubscribe();
    }
}