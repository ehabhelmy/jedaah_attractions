package com.spade.ja.ui.Home.explore;

import com.spade.ja.datalayer.pojo.response.category.Cats;
import com.spade.ja.datalayer.pojo.response.venues.Venue;
import com.spade.ja.ui.Base.listener.BaseView;
import com.spade.ja.ui.Base.listener.ErrorView;
import com.spade.ja.ui.Base.listener.ProgressView;
import com.spade.ja.ui.Home.explore.pojo.Event;
import com.spade.ja.usecase.Unsubscribable;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by ehab on 12/11/17.
 */

public interface ExploreContract {

    interface View extends BaseView,ErrorView,ProgressView {
        void setupTopEvents(List<Event> events);
        void setupNearbyEvents(List<Event> events);
        void setupTopVenues(List<Venue> data);
        void setupNearbyVenues(List<Venue> data);
        boolean isLocationPermissionGranted();
        boolean isLocationEnabled();
        void showErrorLocationNotEnabled();
        void getLatitudeAndLongitude();
        void setupCategories(List<Cats> categories);
        void hideNearByEvents();
        void hideNearByVenues();
    }

    interface Presenter extends Unsubscribable{
        void openLocationSettings();
        void loadNearByEventsAfterLocationEnabled(LatLng latLng);
        void loadNearByVenuesAfterLocationEnabled(LatLng latLng);
        void showEventInner(int id);
        void showVenueInner(int id);
        void like(int id);
        void venueLike(int id);
    }
}
