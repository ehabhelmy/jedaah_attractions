package com.spade.ja.ui.Home.map;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.spade.ja.datalayer.pojo.response.allnearby.AllNearByResponse;
import com.spade.ja.datalayer.pojo.response.events.EventsResponse;
import com.spade.ja.datalayer.pojo.response.like.LikeResponse;
import com.spade.ja.datalayer.pojo.response.venues.VenuesResponse;
import com.spade.ja.ui.Base.BasePresenter;
import com.spade.ja.ui.Base.listener.BaseCallback;
import com.spade.ja.usecase.like.Like;
import com.spade.ja.usecase.like.LikeAttractions;
import com.spade.ja.usecase.like.LikeVenues;
import com.spade.ja.usecase.nearbyall.NearByAll;
import com.spade.ja.usecase.nearbyattractions.NearByAttractions;
import com.spade.ja.usecase.nearbyevents.NearByEvents;
import com.spade.ja.usecase.nearbyvenues.NearByVenues;

import javax.inject.Inject;

/**
 * Created by Roma on 2/3/2018.
 */
public class MapPresenter extends BasePresenter<MapContract.View> implements MapContract.Presenter {

    private NearByEvents nearByEvents;
    private NearByVenues nearByVenues;
    private NearByAttractions nearByAttractions;
    private NearByAll nearByAll;
    private Like eventsLike;
    private LikeVenues venuesLike;
    private LikeAttractions attractionsLike;

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
    private BaseCallback<AllNearByResponse> allNearByResponseBaseCallback = new BaseCallback<AllNearByResponse>() {
        @Override
        public void onSuccess(AllNearByResponse model) {
            if (isViewAlive.get()) {
                if (model.getSuccess()) {
                    getView().setupData(DataConverter.convertAllNearByToView(model.getData().getResult()));
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
    public MapPresenter(NearByEvents nearByEvents, NearByVenues nearByVenues, NearByAttractions nearByAttractions, NearByAll nearByAll, Like eventsLike, LikeVenues venuesLike, LikeAttractions attractionsLike) {
        this.nearByEvents = nearByEvents;
        this.nearByVenues = nearByVenues;
        this.nearByAttractions = nearByAttractions;
        this.nearByAll = nearByAll;
        this.eventsLike = eventsLike;
        this.venuesLike = venuesLike;
        this.attractionsLike = attractionsLike;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
    }


    public void getAllData() {
        Location location = getView().getCurrentLocation();
        if (location != null) {
            nearByAll.getNearByAll((new LatLng(location.getLatitude(), location.getLongitude())), allNearByResponseBaseCallback, true);
        } else {
            nearByAll.getNearByAll(new LatLng(40, 40), allNearByResponseBaseCallback, true);
        }
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
        Location location = getView().getCurrentLocation();
        if (location != null) {
            nearByAttractions.getNearByAttractions((new LatLng(location.getLatitude(), location.getLongitude())), nearbyVenuesResponseBaseCallback, true);
        } else {
            nearByAttractions.getNearByAttractions(new LatLng(40, 40), nearbyVenuesResponseBaseCallback, true);
        }
    }

    @Override
    public void unSubscribe() {
        nearByAll.unSubscribe();
        nearByEvents.unSubscribe();
        nearByVenues.unSubscribe();
        nearByAttractions.unSubscribe();
        eventsLike.unSubscribe();
        venuesLike.unSubscribe();
        attractionsLike.unSubscribe();
    }
    @Override
    public void showEventInner(int id) {
        jaNavigationManager.showEventInner(id);
    }

    @Override
    public void showVenuesInner(int id) {
        jaNavigationManager.showVenueInner(id);
    }

    @Override
    public void showAttractionInner(int id) {
        jaNavigationManager.showAttractionInner(id);
    }

    @Override
    public void like(int id) {
        eventsLike.like(id,likeResponseBaseCallback);
    }

    @Override
    public void venuesLike(int id) {
        venuesLike.like(id,likeResponseBaseCallback);
    }

    @Override
    public void attractionsLike(int id) {
        attractionsLike.like(id,likeResponseBaseCallback);
    }

}
