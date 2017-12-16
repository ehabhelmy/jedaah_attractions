package com.example.ehab.japroject.ui.Home.events.nearby_events;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.EventsResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.nearbyevents.NearByEvents;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

/**
 * Created by ehab on 12/16/17.
 */

public class NearByEventsPresenter extends BasePresenter<NearByEventsContract.View> implements NearByEventsContract.Presenter {

    private NearByEvents nearByEvents;

    @Inject
    public NearByEventsPresenter(NearByEvents nearByEvents) {
        this.nearByEvents = nearByEvents;
    }

    private BaseCallback<EventsResponse> responseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()){
                    getView().setupNearByEvents(EventsAdapter.convertIntoEventUi(model.getData()));
                }else {
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

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        if (getView().isLocationPermissionGranted()) {
            if (getView().isLocationEnabled()) {
                getView().getLatitudeAndLongitude();
            } else {
                getView().showErrorLocationNotEnabled();
            }
        } else {
            // TODO : show error location permission is disabled
        }
    }

    @Override
    public void unSubscribe() {
        nearByEvents.unSubscribe();
    }

    @Override
    public void loadNearByEventsAfterLocationEnabled(LatLng latLng) {
        nearByEvents.getNearbyEvents(latLng, responseBaseCallback);
    }

    @Override
    public void openLocationSettings() {
        jaNavigationManager.showLocationSettings();
    }
}
