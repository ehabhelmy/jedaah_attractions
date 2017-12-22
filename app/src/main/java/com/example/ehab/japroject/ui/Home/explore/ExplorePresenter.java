package com.example.ehab.japroject.ui.Home.explore;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.categories.Categories;
import com.example.ehab.japroject.usecase.nearbyevents.NearByEvents;
import com.example.ehab.japroject.usecase.topevents.TopEvents;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

/**
 * Created by ehab on 12/11/17.
 */

public class ExplorePresenter extends BasePresenter<ExploreContract.View> implements ExploreContract.Presenter {

    private TopEvents topEvents;
    private NearByEvents nearByEvents;
    private Categories categories;

    private BaseCallback<EventsResponse> topEventsResponseBaseCallback = new BaseCallback<EventsResponse>() {
        @Override
        public void onSuccess(EventsResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupTopEvents(EventsAdapter.convertIntoEventUi(model.getData()));
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
                    getView().setupNearbyEvents(EventsAdapter.convertIntoEventUi(model.getData()));
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
    private BaseCallback<Category> categoryBaseCallback = new BaseCallback<Category>() {
        @Override
        public void onSuccess(Category model) {
            if (isViewAlive.get()){
                if (model.getSuccess()){
                    getView().setupCategories(model.getData());
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
    public ExplorePresenter(TopEvents topEvents, NearByEvents nearByEvents,Categories categories) {
        this.topEvents = topEvents;
        this.nearByEvents = nearByEvents;
        this.categories = categories;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        //TODO : should call all related use cases to fetch the data
        topEvents.getTopEvents(topEventsResponseBaseCallback,true);
        categories.getCategories(categoryBaseCallback,true);
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
        topEvents.unSubscribe();
        categories.unSubscribe();
        nearByEvents.unSubscribe();
    }

    @Override
    public void openLocationSettings() {
        jaNavigationManager.showLocationSettings();
    }

    @Override
    public void loadNearByEventsAfterLocationEnabled(LatLng latLng) {
        nearByEvents.getNearbyEvents(latLng, nearbyEventsResponseBaseCallback,true);
    }
}
