package com.example.ehab.japroject.ui.map;

import android.location.Location;
import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.usecase.nearbyevents.NearByEvents;
import com.example.ehab.japroject.usecase.nearbyvenues.NearByVenues;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

/**
 * Created by Roma on 2/3/2018.
 */
public class MapPresenter extends BasePresenter<MapContract.View> implements MapContract.Presenter {

    @Inject
    NearByEvents nearByEvents;

    @Inject
    NearByVenues nearByVenues;

    private BaseCallback<VenuesResponse> venuesResponseBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupData(DataConverter.convertVenueIntoData(model.getData()));
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

    private BaseCallback<EventsResponse> nearbyEventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupData(DataConverter.convertEventIntoData(model.getData()));
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
            }
        }
    };

    private BaseCallback<VenuesResponse> nearbyVenuesResponseBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupData(DataConverter.convertVenueIntoData(model.getData()));
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
            }
        }
    };

    @Inject
    public MapPresenter(NearByEvents nearByEvents, NearByVenues nearByVenues) {
        this.nearByEvents = nearByEvents;
        this.nearByVenues = nearByVenues;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }


    public void getAllData() {
    }

    public void getNearByEvents() {
        Location location = getView().getCurrentLocation();
        if (location != null) {
            nearByEvents.getNearbyEvents((new LatLng(location.getLatitude(), location.getLongitude())), nearbyEventsResponseBaseCallback, true);
        } else {
            nearByEvents.getNearbyEvents(new LatLng(40, 40), nearbyEventsResponseBaseCallback, true);
        }
    }

    public void getNearByVenues() {
        Location location = getView().getCurrentLocation();
        if (location != null) {
            nearByVenues.getNearbyVenues((new LatLng(location.getLatitude(), location.getLongitude())), nearbyVenuesResponseBaseCallback, true);
        } else {
            nearByVenues.getNearbyVenues(new LatLng(40, 40), nearbyVenuesResponseBaseCallback, true);
        }
    }

    public void getNearByAttractions() {
    }
}
