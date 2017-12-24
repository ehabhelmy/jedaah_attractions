package com.example.ehab.japroject.ui.Home.eventsinner.eventdetails;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.eventinner.EventInnerResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.eventsinner.eventdetails.adapter.EventDetailsAdapter;
import com.example.ehab.japroject.usecase.eventinner.EventInner;
import com.example.ehab.japroject.util.Constants;

import javax.inject.Inject;

/**
 * Created by ehab on 12/20/17.
 */

public class EventInnerPresenter extends BasePresenter<EventInnerContract.View> implements EventInnerContract.Presenter {


    private EventInner eventInner;
    private BaseCallback<EventInnerResponse> eventInnerResponseBaseCallback = new BaseCallback<EventInnerResponse>() {
        @Override
        public void onSuccess(EventInnerResponse model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().setupEventsInner(EventDetailsAdapter.convertIntoEventDetailsUi(model.getData()));
                }
            }
        }

        @Override
        public void onError(String message) {
            getView().showError(message);
        }
    };

    @Inject
    public EventInnerPresenter(EventInner eventInner) {
        this.eventInner = eventInner;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        //TODO : get id from extras and call api event inner
        int id = extras.getInt(Constants.EVENT_ID);
        eventInner.getEventDetails(eventInnerResponseBaseCallback,id);
    }

    @Override
    public void unSubscribe() {
        eventInner.unSubscribe();
    }

    @Override
    public void openNavigationView(double lat, double lng) {
        jaNavigationManager.openNavigationView(lat,lng);
    }
}
