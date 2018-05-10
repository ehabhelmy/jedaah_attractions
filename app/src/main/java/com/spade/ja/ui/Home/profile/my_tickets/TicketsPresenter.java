package com.spade.ja.ui.Home.profile.my_tickets;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.attractionhistory.AttractionOrderHistoryResponse;
import com.spade.ja.datalayer.pojo.response.contactus.ContactUsResponse;
import com.spade.ja.datalayer.pojo.response.history.upcoming.HistoryEvents;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.profile.my_tickets.pojo.HistoryEventsUi;
import com.spade.ja.usecase.cancel.AttractionCancel;
import com.spade.ja.usecase.cancel.EventCancel;
import com.spade.ja.usecase.historyattraction.AttractionPast;
import com.spade.ja.usecase.historyattraction.AttractionUpcoming;
import com.spade.ja.usecase.pastevents.PastEvents;
import com.spade.ja.usecase.upcomingevents.UpcomingEvents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.spade.ja.ui.Home.profile.my_tickets.adapter.HistoryEventsAdapter.convertIntoHistoryUi;
import static com.spade.ja.ui.Home.profile.my_tickets.adapter.HistoryEventsAdapter.convertIntoHistoryUiAttraction;

/**
 * Created by Romisaa on 12/17/2017.
 */

public class TicketsPresenter extends BasePresenter<TicketsContract.View> implements TicketsContract.Presenter{

    private UpcomingEvents upcomingEvents;
    private PastEvents pastEvents;
    private AttractionUpcoming attractionUpcoming;
    private AttractionPast attractionPast;
    private EventCancel eventCancel;
    private AttractionCancel attractionCancel;
    private List<HistoryEventsUi> historyEvents = new ArrayList<>();
    private List<HistoryEventsUi> eventsPast = new ArrayList<>();
    private boolean upcoming = false;
    private boolean past = false;
    private BaseCallback<HistoryEvents> upcomingCallBack = new BaseCallback<HistoryEvents>() {
        @Override
        public void onSuccess(HistoryEvents model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    if (upcoming){
                        Collections.shuffle(historyEvents);
                        getView().setupUpcomingList(historyEvents);
                    }else {
                        upcoming = true;
                        historyEvents.addAll(convertIntoHistoryUi(model.getData(),true));
                    }
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
                    if (past){
                        Collections.shuffle(eventsPast);
                        getView().setupPastList(eventsPast);
                    }else {
                        past = true;
                        eventsPast.addAll(convertIntoHistoryUi(model.getData(),false));
                    }
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
    private BaseCallback<AttractionOrderHistoryResponse> attractionOrderHistoryResponseBaseCallback = new BaseCallback<AttractionOrderHistoryResponse>() {
        @Override
        public void onSuccess(AttractionOrderHistoryResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    if (upcoming){
                        Collections.shuffle(historyEvents);
                        getView().setupUpcomingList(historyEvents);
                    }else {
                        upcoming = true;
                        historyEvents.addAll(convertIntoHistoryUiAttraction(model.getData(),true));
                    }
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
    private BaseCallback<AttractionOrderHistoryResponse> responseBaseCallback = new BaseCallback<AttractionOrderHistoryResponse>() {
        @Override
        public void onSuccess(AttractionOrderHistoryResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    if (past){
                        Collections.shuffle(eventsPast);
                        getView().setupPastList(eventsPast);
                    }else {
                        past = true;
                        eventsPast.addAll(convertIntoHistoryUiAttraction(model.getData(),false));
                    }
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

    private BaseCallback<ContactUsResponse> contactUsResponseBaseCallback = new BaseCallback<ContactUsResponse>() {
        @Override
        public void onSuccess(ContactUsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {

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
    public TicketsPresenter(UpcomingEvents upcomingEvents, PastEvents pastEvents, AttractionUpcoming attractionUpcoming, AttractionPast attractionPast, EventCancel eventCancel, AttractionCancel attractionCancel) {
        this.upcomingEvents = upcomingEvents;
        this.pastEvents = pastEvents;
        this.attractionUpcoming = attractionUpcoming;
        this.attractionPast = attractionPast;
        this.eventCancel = eventCancel;
        this.attractionCancel = attractionCancel;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        upcomingEvents.getUpcomingEvents(upcomingCallBack);
        pastEvents.getPastEvents(pastCallBack);
        attractionUpcoming.getUpcomingAttractions(attractionOrderHistoryResponseBaseCallback);
        attractionPast.getPastAttractions(responseBaseCallback);
    }

    @Override
    public void unSubscribe() {
        upcomingEvents.unSubscribe();
        pastEvents.unSubscribe();
        attractionPast.unSubscribe();
        attractionUpcoming.unSubscribe();
        eventCancel.unSubscribe();
        attractionCancel.unSubscribe();
    }

    @Override
    public void cancelOrder(String type, int id) {
        if (type.equalsIgnoreCase("event")){
            eventCancel.eventCancel(id,contactUsResponseBaseCallback);
        }else {
            attractionCancel.attractionCancel(id,contactUsResponseBaseCallback);
        }
    }
}
