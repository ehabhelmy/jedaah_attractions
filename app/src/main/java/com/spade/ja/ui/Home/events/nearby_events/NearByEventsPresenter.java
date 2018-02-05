package com.spade.ja.ui.Home.events.nearby_events;

import android.os.Bundle;

import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.ui.Home.explore.adapter.EventsAdapter;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.nearbyevents.NearByEvents;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

/**
 * Created by ehab on 12/16/17.
 */

public class NearByEventsPresenter extends BasePresenter<NearByEventsContract.View> implements NearByEventsContract.Presenter {

    private NearByEvents nearByEvents;
    private Like like;

    @Inject
    public NearByEventsPresenter(NearByEvents nearByEvents,Like like) {
        this.nearByEvents = nearByEvents;
        this.like = like;
    }

    private BaseCallback<EventsResponse> responseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()){
                    getView().setupNearByEvents(EventsAdapter.convertIntoEventUi(model.getData()));
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

    private BaseCallback<LikeResponse> likeResponseBaseCallback = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            // do nothing
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
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
        nearByEvents.getNearbyEvents(latLng, responseBaseCallback,true);
    }

    @Override
    public void openLocationSettings() {
        jaNavigationManager.showLocationSettings();
    }

    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }

    @Override
    public void like(int id) {
        like.like(id,likeResponseBaseCallback);
    }
}
