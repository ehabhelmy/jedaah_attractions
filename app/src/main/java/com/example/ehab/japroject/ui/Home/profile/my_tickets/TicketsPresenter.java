package com.example.ehab.japroject.ui.Home.profile.my_tickets;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.pastevents.PastEvents;
import com.example.ehab.japroject.usecase.upcomingevents.UpcomingEvents;

import javax.inject.Inject;

import static com.example.ehab.japroject.ui.Home.profile.my_tickets.adapter.HistoryEventsAdapter.convertIntoHistoryUi;
/**
 * Created by Romisaa on 12/17/2017.
 */

public class TicketsPresenter extends BasePresenter<TicketsContract.View> implements TicketsContract.Presenter{

    private UpcomingEvents upcomingEvents;
    private PastEvents pastEvents;
    private BaseCallback<HistoryEvents> upcomingCallBack = new BaseCallback<HistoryEvents>() {
        @Override
        public void onSuccess(HistoryEvents model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupUpcomingList(convertIntoHistoryUi(model.getData()));
                }else {
                    getView().showError("Server Error");
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };
    private BaseCallback<HistoryEvents> pastCallBack = new BaseCallback<HistoryEvents>() {
        @Override
        public void onSuccess(HistoryEvents model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupPastList(convertIntoHistoryUi(model.getData()));
                }else {
                    getView().showError("Server Error");
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };
    @Inject
    public TicketsPresenter(UpcomingEvents upcomingEvents, PastEvents pastEvents) {
        this.upcomingEvents = upcomingEvents;
        this.pastEvents = pastEvents;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        upcomingEvents.getUpcomingEvents(upcomingCallBack);
        pastEvents.getPastEvents(pastCallBack);
    }

    @Override
    public void unSubscribe() {
        upcomingEvents.unSubscribe();
        pastEvents.unSubscribe();
    }
}
