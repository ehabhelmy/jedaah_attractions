package com.example.ehab.japroject.ui.Home.explore;

import android.os.Bundle;

import com.example.ehab.japroject.datalayer.pojo.response.category.Category;
import com.example.ehab.japroject.datalayer.pojo.response.events.EventsResponse;
import com.example.ehab.japroject.datalayer.pojo.response.like.LikeResponse;
import com.example.ehab.japroject.datalayer.pojo.response.venues.VenuesResponse;
import com.example.ehab.japroject.ui.Base.BasePresenter;
import com.example.ehab.japroject.ui.Base.listener.BaseCallback;
import com.example.ehab.japroject.ui.Home.explore.adapter.EventsAdapter;
import com.example.ehab.japroject.usecase.categories.Categories;
import com.example.ehab.japroject.usecase.like.Like;
import com.example.ehab.japroject.usecase.like.LikeVenues;
import com.example.ehab.japroject.usecase.nearbyevents.NearByEvents;
import com.example.ehab.japroject.usecase.nearbyvenues.NearByVenues;
import com.example.ehab.japroject.usecase.topevents.TopEvents;
import com.example.ehab.japroject.usecase.topvenues.TopVenues;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

/**
 * Created by ehab on 12/11/17.
 */

public class ExplorePresenter extends BasePresenter<ExploreContract.View> implements ExploreContract.Presenter {

    private TopEvents topEvents;
    private NearByEvents nearByEvents;
    private NearByVenues nearByVenues;
    private TopVenues topVenues;
    private Categories categories;
    private Like like;
    private LikeVenues likeVenues;

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
                //getView().showError(message);
                getView().hideNearByEvents();
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

    private BaseCallback<LikeResponse> likeResponseBaseCallback = new BaseCallback<LikeResponse>() {
        @Override
        public void onSuccess(LikeResponse model) {
            if (isViewAlive.get()){
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()){
                getView().showError(message);
            }
        }
    };

    private BaseCallback<VenuesResponse> venuesResponseBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupTopVenues(model.getData());
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

    private BaseCallback<VenuesResponse> nearbyVenuesResponseBaseCallback = new BaseCallback<VenuesResponse>() {
        @Override
        public void onSuccess(VenuesResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupNearbyVenues(model.getData());
                }
            }
        }

        @Override
        public void onError(String message) {
            if (isViewAlive.get()) {
                //getView().showError(message);
                getView().hideNearByVenues();
            }
        }
    };

    @Inject
    public ExplorePresenter(TopEvents topEvents, NearByEvents nearByEvents,TopVenues topVenues,NearByVenues nearByVenues,Categories categories,Like like,LikeVenues likeVenues) {
        this.topEvents = topEvents;
        this.nearByEvents = nearByEvents;
        this.topVenues = topVenues;
        this.nearByVenues = nearByVenues;
        this.categories = categories;
        this.like = like;
        this.likeVenues = likeVenues;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        //TODO : should call all related use cases to fetch the data
        topEvents.getTopEvents(topEventsResponseBaseCallback,true);
        categories.getCategories(categoryBaseCallback,true);
        topVenues.getTopVenues(venuesResponseBaseCallback,true);
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
        topVenues.unSubscribe();
        nearByVenues.unSubscribe();
        like.unSubscribe();
    }

    @Override
    public void openLocationSettings() {
        jaNavigationManager.showLocationSettings();
    }

    @Override
    public void loadNearByEventsAfterLocationEnabled(LatLng latLng) {
        nearByEvents.getNearbyEvents(latLng, nearbyEventsResponseBaseCallback,true);
    }

    @Override
    public void loadNearByVenuesAfterLocationEnabled(LatLng latLng) {
        nearByVenues.getNearbyVenues(latLng,nearbyVenuesResponseBaseCallback,true);
    }

    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }

    @Override
    public void showVenueInner(int id) {
        jaNavigationManager.showVenueInner(id);
    }

    @Override
    public void like(int id) {
        like.like(id,likeResponseBaseCallback);
    }

    @Override
    public void venueLike(int id) {
        likeVenues.like(id,likeResponseBaseCallback);
    }
}
